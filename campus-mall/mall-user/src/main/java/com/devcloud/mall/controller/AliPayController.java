package com.devcloud.mall.controller;

import com.alipay.api.AlipayApiException;
import com.devcloud.mall.common.R;
import com.devcloud.mall.constant.AliPayConstant;
import com.devcloud.mall.domain.AliPay;
import com.devcloud.mall.domain.Orders;
import com.devcloud.mall.service.AliPayService;
import com.devcloud.mall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * @author 吴员外
 * @date 2022/11/6 11:11
 */
@RestController
@RequestMapping("/alipay")
public class AliPayController {

    @Autowired
    private AliPayService aliPayService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/pay")
    public R pay(@RequestBody AliPay aliPay) throws IOException, AlipayApiException {
        //查询订单金额
        String orderId = aliPay.getTradeNo();
        Orders order = orderService.getById(orderId);
        aliPay.setTotalAmount(order.getMoney().doubleValue());
        aliPay.setSubject(orderId);
        //设置支付表单的过期时间，超过时间不能支付
        aliPay.setExpireTime(new Date(order.getCreateTime().getTime() + AliPayConstant.EXPIRE_TIME));

        String form = aliPayService.pay(aliPay);
        return R.ok().data("form",form);
    }

    @PostMapping("/notify")
    public R payNotify(HttpServletRequest request) throws AlipayApiException {
        aliPayService.payNotify(request);
        return R.ok();
    }

}
