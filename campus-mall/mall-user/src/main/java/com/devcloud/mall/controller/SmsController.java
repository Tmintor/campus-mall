package com.devcloud.mall.controller;

import com.devcloud.mall.common.R;
import com.devcloud.mall.service.SmsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 吴员外
 * @date 2022/10/5 13:42
 *    短信控制器
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService smsService;

    @ApiOperation("获取注册验证码")
    @GetMapping("/code/{phone}")
    public R getVerificationCode(@PathVariable String phone) {
        boolean flag = smsService.getVerificationCode(phone);
        return flag ? R.ok() : R.error().message("短信发送失败，请稍后重试");
    }

}
