package com.devcloud.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devcloud.mall.domain.User;
import com.devcloud.mall.mapper.UserMapper;
import com.devcloud.mall.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author 吴员外
 * @date 2022/10/31 21:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


    @Override
    public Map<String, Object> getUserList(Integer current, Integer limit) {
        Page<User> page = new Page<>(current, limit);
        baseMapper.selectPage(page, null);
        Map<String, Object> map = BeanUtil.beanToMap(page, "records", "total", "size", "current");
        map.put("hasNext", page.hasNext());
        map.put("hasPrevious", page.hasPrevious());
        return map;
    }

    @Override
    public User getUserInfo(String id) {
        return baseMapper.selectById(id);
    }

    @Override
    public void forbiddenUser(User user) {
        baseMapper.updateById(user);
    }

    @Override
    public Integer getRegisterNumByDay(Date date) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        String begin = DateUtil.format(date, "yyyy-MM-dd 00:00:00");
        String after = DateUtil.format(date, "yyyy-MM-dd 23:59:59");
        lambdaQueryWrapper.between(User::getCreateTime, begin, after);
        return baseMapper.selectCount(lambdaQueryWrapper);
    }

}
