package com.devcloud.mall.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devcloud.mall.domain.User;
import com.devcloud.mall.domain.UserDetail;
import com.devcloud.mall.domain.vo.LoginPhoneVo;
import com.devcloud.mall.domain.vo.RegisterVo;
import com.devcloud.mall.mapper.UserMapper;
import com.devcloud.mall.service.UserService;
import com.devcloud.mall.utils.JwtUtil;
import com.devcloud.mall.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService,UserDetailsService{

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void register(RegisterVo registerVo) {
        //查询验证码是否正确
        String mobile = registerVo.getMobile();
        String code = redisCache.getCacheObject(mobile);
        if (!StringUtils.hasText(code) || !registerVo.getCode().equals(code)) {
            throw new RuntimeException("验证码错误");
        }
        //验证用户名是否存在
        String username = registerVo.getUsername();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new RuntimeException("用户名已存在");
        }
        //验证手机号是否已经注册
        QueryWrapper<User> queryPhone = new QueryWrapper<>();
        queryPhone.eq("mobile", mobile);
        Integer count1 = baseMapper.selectCount(queryPhone);
        if (count1 > 0) {
            throw new RuntimeException("该手机号已注册");
        }

        //插入新用户
        User user = new User();
        user.setUsername(username);
        user.setMobile(mobile);
        user.setIsActive(1);
        user.setPassword(passwordEncoder.encode(registerVo.getPassword()));
        baseMapper.insert(user);

    }

    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //判断认证成功还是失败
        if (authenticate == null) {
            throw new RuntimeException("用户名或密码错误");
        }

        //判断用户是否被禁用
        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        if (0 == userDetail.getUser().getIsActive()) {
            throw new RuntimeException("该用户已被禁用");
        }
        //认证成功生成token，存放redis
        String userId = userDetail.getUser().getId();
        String token = JwtUtil.createJWT(userId);
        redisCache.setCacheObject(userId,userDetail,8, TimeUnit.HOURS);

        return token;
    }

    @Override
    public void logout() {
        //从SecurityContextHolder拿到认证信息
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();

        //删除redis中的token
        String id = userDetail.getUser().getId();
        redisCache.deleteObject(id);
    }

    @Override
    public String loginPhone(LoginPhoneVo loginPhoneVo) {
        //redis获取验证码
        String mobile = loginPhoneVo.getMobile();
        String code = redisCache.getCacheObject(mobile);

        if (!StringUtils.hasText(code) || !loginPhoneVo.getCode().equals(code)) {
            throw new RuntimeException("验证码错误");
        }
        //验证手机号是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        User user = baseMapper.selectOne(queryWrapper);
        //没有注册的话自动注册
        if (Objects.isNull(user)) {
            user = new User();
            //生成id
            user.setId(new Sequence().nextId() + "");
            //随机生成用户名
            user.setUsername("用户" + RandomUtil.randomString(10));
            user.setMobile(mobile);
            user.setIsActive(1);
            baseMapper.insert(user);
        }
        String userId = user.getId();
        String token = JwtUtil.createJWT(userId);
        redisCache.setCacheObject(userId,new UserDetail(user,null),1, TimeUnit.HOURS);
        return token;
    }

    @Override
    public User getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String userId;
        try {
            userId = JwtUtil.parseJWT(token).getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("token不合法");
        }
        UserDetail userDetail = redisCache.getCacheObject(userId);
        if (Objects.isNull(userDetail)) {
            throw new RuntimeException("用户未登录");
        }
        return userDetail.getUser().setPassword(null);
    }

    @Override
    public User getUserInfoById(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void modifyUserInfo(User user) {
        //更新用户信息
        baseMapper.updateById(user);
        //更新后需要从缓存中取出用户信息更新
        UserDetail userDetail = redisCache.getCacheObject(user.getId());
        User updateUser = baseMapper.selectById(user.getId());
        userDetail.setUser(updateUser);
        redisCache.setCacheObject(updateUser.getId(), userDetail);
    }

    @Override
    @Transactional
    public void updateAvatar(User user) {
        baseMapper.updateById(user);
        //更新缓存里的用户信息
        UserDetail userDetail = redisCache.getCacheObject(user.getId());
        userDetail.getUser().setAvatar(user.getAvatar());
        redisCache.setCacheObject(user.getId(), userDetail);
    }


    //UserDetailService方法重写
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = baseMapper.selectOne(queryWrapper);

        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        //TODO 查询用户的权限信息

        return new UserDetail(user, null);
    }
}
