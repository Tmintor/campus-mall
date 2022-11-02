package com.devcloud.mall.controller;

import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.Admin;
import com.devcloud.mall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 吴员外
 * @date 2022/10/25 16:45
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public R login(@RequestBody Admin admin) {
        String token = adminService.login(admin);
        return R.ok().data("token", token);
    }

    @PostMapping("/logout")
    public R logout() {
        adminService.logout();
        return R.ok();
    }
}
