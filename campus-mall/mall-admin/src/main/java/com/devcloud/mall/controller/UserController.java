package com.devcloud.mall.controller;

import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.User;
import com.devcloud.mall.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 吴员外
 * @date 2022/10/31 22:20
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation("用户列表")
    @GetMapping("/list/{page}/{limit}")
    public R getUserList(@PathVariable Integer page, @PathVariable Integer limit) {
        Map<String, Object> map = userService.getUserList(page, limit);
        return R.ok().data(map);
    }

    @ApiOperation("用户详细")
    @GetMapping("/userInfo/{id}")
    public R getUserInfo(@PathVariable String id) {
        User user = userService.getUserInfo(id);
        return R.ok().data("userInfo", user);
    }

    @ApiOperation("用户禁用/解封")
    @PutMapping("/status")
    public R forbiddenUser(@RequestBody User user) {
        userService.forbiddenUser(user);
        return R.ok();
    }

}
