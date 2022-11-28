package com.devcloud.mall.listener;

import com.devcloud.mall.domain.Orders;
import com.devcloud.mall.mapper.GoodsMapper;
import com.devcloud.mall.mapper.OrderMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author 吴员外
 * @date 2022/11/13 12:11
 */
@Component
public class RabbitMQListener {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @RabbitListener(queues = "dead-letter-queue")
    @Transactional
    public void receive(Message message) {
        //30分钟到查询订单是否支付
        String orderId = new String(message.getBody());
        System.out.println("-----------订单" + orderId + "从延迟队列收到------------" );
        Orders orders = orderMapper.selectById(orderId);
        //创建订单方法抛出异常后，已经进入消息队列的订单查询出来是空，直接在消费消息后return
        if (Objects.isNull(orders)) {
            return;
        }
        //未支付的话取消订单，解库存
        if (orders.getStatus() == 0) {
            //设置订单取消
            orders.setStatus(-1);
            orderMapper.updateById(orders);
            //解库存
            goodsMapper.incrGoodsNumber(orders.getGoodsId(), orders.getNumber());
        }
    }
}
