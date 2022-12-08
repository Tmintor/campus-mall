package com.devcloud.mall.controller;

import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.User;
import com.devcloud.mall.domain.vo.LoginPhoneVo;
import com.devcloud.mall.domain.vo.RegisterVo;
import com.devcloud.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 吴员外
 * @date 2022/10/30 16:47
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("注册")
    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo) {
        userService.register(registerVo);
        return R.ok();
    }

    @ApiOperation("用户名密码登录")
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        String token = userService.login(user);
        return R.ok().data("token", token);
    }

    @ApiOperation("手机验证码登录")
    @PostMapping("/login/phone")
    public R loginPhone(@RequestBody LoginPhoneVo loginPhoneVo) {
        String token = userService.loginPhone(loginPhoneVo);
        return R.ok().data("token", token);
    }

    @ApiOperation("注销登录")
    @PostMapping("/logout")
    public R logout() {
        userService.logout();
        return R.ok();
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/userInfo")
    public R getUserInfo(HttpServletRequest request) {
        User user = userService.getUserInfo(request);
        return R.ok().data("userInfo", user);
    }

    @ApiOperation("根据id获取用户信息")
    @GetMapping("/userInfo/{id}")
    public R getUserInfoById(@PathVariable String id) {
        User user = userService.getUserInfoById(id);
        return R.ok().data("userInfo", user);
    }

    @ApiOperation("修改个人信息")
    @PutMapping("/userInfo")
    public R modifyUserInfo(@RequestBody User user) {
        userService.modifyUserInfo(user);
        return R.ok();
    }

    @ApiOperation("修改头像")
    @PutMapping("/avatar")
    public R modifyAvatar(@RequestBody User user) {
        userService.updateById(user);
        return R.ok();
    }

}
