package com.devcloud.mall.service.impl;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.devcloud.mall.constant.RabbitConstant;
import com.devcloud.mall.domain.*;
import com.devcloud.mall.mapper.OrderMapper;
import com.devcloud.mall.service.GoodsService;
import com.devcloud.mall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devcloud.mall.service.ShopcarService;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {

    @Autowired
    private AliPayServiceImpl aliPayService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private ShopcarService shopcarService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Sequence sequence;

    @Override
    @Transactional
    public String createOrder(String[] shopcarIds) throws AlipayApiException {

        //查询购物车商品的详细信息
        List<ShopcarDetail> shopcarDetailList = shopcarService.selectShopcarDetailByIds(shopcarIds);
        BigDecimal totalAmount = new BigDecimal(0);//总价格

        //保存交易名称（如果多个订单保存所有订单号，下划线分割）
        StringBuilder subject = new StringBuilder();
        //支付宝请求参数，多个商品同时结算默认traceNo为最后一个订单
        AliPay aliPay = new AliPay();
        //订单
        Orders orders = new Orders();

        for (ShopcarDetail goods : shopcarDetailList) {
            Shopcar shopcar = goods.getShopcar();
            Integer buyNumber = shopcar.getNumber();//购买数量
            Integer amount = goods.getAmount();//剩余库存

            //库存不够
            if (buyNumber > amount) {
                throw new RuntimeException("商品【" + goods.getGoodsName() + "】库存不足，请修改后再结算");
            }

            BigDecimal price = goods.getPrice();//价格

            //锁库存
            Goods newGoods = new Goods();
            newGoods.setId(shopcar.getGoodsId());
            newGoods.setNumber(amount - buyNumber);
            goodsService.updateById(newGoods);

            //创建未支付订单
            orders.setId(sequence.nextId() + "");
            orders.setGoodsId(shopcar.getGoodsId());
            orders.setCustomerId(shopcar.getUserId());
            orders.setStatus(0);
            orders.setNumber(buyNumber);
            orders.setMoney(price.multiply(new BigDecimal(buyNumber)));
            //放入数据库
            orderService.save(orders);

            //加总金额
            totalAmount = totalAmount.add(orders.getMoney());

            //放入rabbitMq延时队列
            //方法抛出异常后，已经进入消息队列的订单查询出来是空，直接在消费消息后return
            rabbitTemplate.convertAndSend(
                    RabbitConstant.DELAYED_EXCHANGE,
                    RabbitConstant.DELAYED_ROUTING_KEY,
                    orders.getId(),
                    new CorrelationData(orders.getId()));
            System.out.println("-----------订单" + orders.getId() + "进入队列------------" );
            subject.append(orders.getId()).append("_");
        }

        //调用支付宝支付接口
        aliPay.setTraceNo(orders.getId());
        aliPay.setTotalAmount(totalAmount.doubleValue());
        aliPay.setSubject(subject.toString());
        return aliPayService.pay(aliPay);
    }



}
