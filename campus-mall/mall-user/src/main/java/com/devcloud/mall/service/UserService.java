package com.devcloud.mall.service;

import com.devcloud.mall.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devcloud.mall.domain.vo.LoginPhoneVo;
import com.devcloud.mall.domain.vo.RegisterVo;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
public interface UserService extends IService<User> {

    void register(RegisterVo registerVo);

    String login(User user);

    void logout();

    String loginPhone(LoginPhoneVo loginPhoneVo);

    User getUserInfo(HttpServletRequest request);

    User getUserInfoById(String id);

    void modifyUserInfo(User user);

    void updateAvatar(User user);
}
