package com.devcloud.mall.controller;

import com.alipay.api.AlipayApiException;
import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.AliPay;
import com.devcloud.mall.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 吴员外
 * @date 2022/11/6 11:11
 */
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    @GetMapping("/pay")
    public void pay(AliPay aliPay, HttpServletResponse response) throws IOException, AlipayApiException {
        String form = aliPayService.pay(aliPay);
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = response.getWriter();
        writer.write(form);// 直接将完整的表单html输出到页面
        writer.flush();
        writer.close();
    }

    @PostMapping("/notify")
    public R payNotify(HttpServletRequest request) throws AlipayApiException {
        aliPayService.payNotify(request);
        return R.ok();
    }

}
