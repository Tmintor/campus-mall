package com.devcloud.mall.service;

import com.alipay.api.AlipayApiException;
import com.devcloud.mall.domain.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 * @author tminto
 * @since 2022-10-30
 */
public interface OrderService extends IService<Orders> {

    String createOrder(String[] shopcarIds) throws AlipayApiException;

}
