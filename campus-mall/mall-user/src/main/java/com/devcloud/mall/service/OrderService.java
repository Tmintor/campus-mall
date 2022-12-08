package com.devcloud.mall.service;

import com.alipay.api.AlipayApiException;
import com.devcloud.mall.domain.ConfirmOrder;
import com.devcloud.mall.domain.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.devcloud.mall.domain.dto.OrderDetailDto;
import com.devcloud.mall.domain.vo.ConfirmOrderVo;
import com.devcloud.mall.domain.vo.OrderVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 * @author tminto
 * @since 2022-10-30
 */
public interface OrderService extends IService<Orders> {

    String createOrder(OrderVo[] orderVos) throws AlipayApiException;

    Map<String, Object> getMySoldList(String userId, Integer page, Integer limit);

    List<ConfirmOrder> createConfirmOrder(ConfirmOrderVo[] confirmOrderVo);

    OrderDetailDto getOrderDetail(String orderId);

    Map<String, Object> getMyBuyList(String userId, Integer page, Integer limit);
}
