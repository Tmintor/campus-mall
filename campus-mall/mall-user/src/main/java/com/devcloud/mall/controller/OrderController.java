package com.devcloud.mall.controller;

import com.alipay.api.AlipayApiException;
import com.devcloud.mall.common.R;
import com.devcloud.mall.domain.ConfirmOrder;
import com.devcloud.mall.domain.Orders;
import com.devcloud.mall.domain.dto.OrderDetailDto;
import com.devcloud.mall.domain.vo.ConfirmOrderVo;
import com.devcloud.mall.domain.vo.OrderVo;
import com.devcloud.mall.service.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author tminto
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation("生成确定单")
    @PostMapping("/confirm")
    public R createConfirmOrder(@RequestBody ConfirmOrderVo[] confirmOrders) {
        List<ConfirmOrder> list = orderService.createConfirmOrder(confirmOrders);
        return R.ok().data("confirmOrderList", list);
    }

    @ApiOperation("生成订单")
    @PostMapping("")
    public R createOrder(@RequestBody OrderVo[] orders) throws AlipayApiException {
        String form = orderService.createOrder(orders);
        return R.ok().data("form", form);
    }

    @ApiOperation("获取订单详细")
    @GetMapping("/{orderId}")
    public R getOrder(@PathVariable String orderId) {
        OrderDetailDto orderDetail = orderService.getOrderDetail(orderId);
        return R.ok().data("order", orderDetail);
    }

    @ApiOperation("我卖出的")
    @GetMapping("/sell/list/{userId}/{page}/{limit}")
    public R getMySoldList(@PathVariable String userId,
                           @PathVariable Integer page,
                           @PathVariable Integer limit) {
        Map<String, Object> map = orderService.getMySoldList(userId, page, limit);
        return R.ok().data(map);
    }

    @ApiOperation("我买到的")
    @GetMapping("/buy/list/{userId}/{page}/{limit}")
    public R getMyBuyList(@PathVariable String userId,
                          @PathVariable Integer page,
                          @PathVariable Integer limit) {
        Map<String, Object> map = orderService.getMyBuyList(userId, page, limit);
        return R.ok().data(map);
    }

    @ApiOperation("取消订单")
    @PutMapping("/{orderId}")
    public R cancelOrder(@PathVariable String orderId) {
        orderService.cancelOrder(orderId);
        return R.ok();
    }

    @ApiOperation("删除订单")
    @DeleteMapping("/{orderId}")
    public R deleteOrder(@PathVariable String orderId) {
        orderService.removeById(orderId);
        return R.ok();
    }

    //TODO
    @ApiOperation("确定收货")
    @PostMapping("/delivery")
    public R confirmReceipt(@RequestBody Orders orders) {
        orderService.confirmReceipt(orders.getId());
        return R.ok();
    }

}

