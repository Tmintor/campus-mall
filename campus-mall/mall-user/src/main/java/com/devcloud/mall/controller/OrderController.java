package com.devcloud.mall.controller;


import com.alipay.api.AlipayApiException;
import com.devcloud.mall.common.R;
import com.devcloud.mall.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // TODO 创建订单后内部请求支付接口，返回支付页面，支持多个订单一起支付
    @ApiOperation("/创建订单")
    @PostMapping("/")
    public String createOrder(@RequestBody String[] shopcarIds) throws AlipayApiException {
        return orderService.createOrder(shopcarIds);
    }

    // TODO 获取订单信息，前端根据订单创建时间写还剩多少时间支付
    @ApiOperation("获取订单信息")
    @GetMapping("/{id}")
    public R getOrder(@PathVariable String id) {
        return null;
    }

    //

}

