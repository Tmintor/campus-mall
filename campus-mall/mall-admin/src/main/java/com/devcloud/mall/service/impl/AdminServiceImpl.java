package com.devcloud.mall.service.impl;

import com.devcloud.mall.domain.Admin;
import com.devcloud.mall.domain.AdminDetail;
import com.devcloud.mall.service.AdminService;
import com.devcloud.mall.utils.JwtUtil;
import com.devcloud.mall.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴员外
 * @date 2022/10/26 23:56
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public String login(Admin admin) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(admin.getUsername(), admin.getPassword());

        Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        //判断认证成功还是失败
        if (authenticate == null) {
            throw new RuntimeException("登录失败");
        }

        //认证成功生成token，存放redis
        AdminDetail adminDetail = (AdminDetail) authenticate.getPrincipal();
        String userId = adminDetail.getAdmin().getId();
        String token = JwtUtil.createJWT(userId);

        redisCache.setCacheObject(userId,adminDetail,8, TimeUnit.HOURS);

        return token;
    }

    @Override
    public void logout() {
        //从SecurityContextHolder拿到认证信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        AdminDetail adminDetail = (AdminDetail) authentication.getPrincipal();

        //删除redis中的token
        String id = adminDetail.getAdmin().getId();
        redisCache.deleteObject(id);
    }
}
