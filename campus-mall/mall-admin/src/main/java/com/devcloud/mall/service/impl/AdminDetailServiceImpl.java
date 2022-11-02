package com.devcloud.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.devcloud.mall.domain.Admin;
import com.devcloud.mall.domain.AdminDetail;
import com.devcloud.mall.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 吴员外
 * @date 2022/10/26 23:51
 */
@Service
public class AdminDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getUsername, username);
        Admin admin = adminMapper.selectOne(queryWrapper);

        if (Objects.isNull(admin)) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        //TODO 查询用户的权限信息(此处写死)
        //List<String> permissions = Arrays.asList("test", "admin");

        return new AdminDetail(admin);
    }

}
