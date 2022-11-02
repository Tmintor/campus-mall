package com.devcloud.mall.service.impl;

import com.devcloud.mall.domain.Order;
import com.devcloud.mall.mapper.OrderMapper;
import com.devcloud.mall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
