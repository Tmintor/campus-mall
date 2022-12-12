package com.devcloud.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.devcloud.mall.domain.User;

import java.util.Date;
import java.util.Map;


/**
 *
 * @author tminto
 * @since 2022-10-30
 */
public interface UserService extends IService<User> {


    Map<String, Object> getUserList(Integer page, Integer limit);

    User getUserInfo(String id);

    void forbiddenUser(User user);

    Integer getRegisterNumByDay(Date date);
}
