package com.devcloud.mall.service.impl;

import com.alipay.api.AlipayApiException;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.devcloud.mall.constant.RabbitConstant;
import com.devcloud.mall.domain.*;
import com.devcloud.mall.domain.dto.OrderDetailDto;
import com.devcloud.mall.domain.dto.OrderListDto;
import com.devcloud.mall.domain.vo.ConfirmOrderVo;
import com.devcloud.mall.domain.vo.OrderVo;
import com.devcloud.mall.exception.AlipayException;
import com.devcloud.mall.mapper.OrderMapper;
import com.devcloud.mall.service.GoodsService;
import com.devcloud.mall.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.devcloud.mall.service.ShopcarService;
import com.devcloud.mall.utils.BeanUtils;
import com.devcloud.mall.utils.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author tminto
 * @since 2022-10-30
 */
@Slf4j
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
    private RedisCache redisCache;

    @Autowired
    private Sequence sequence;

    @Override
    @Transactional
    public String createOrder(OrderVo[] orderVos) throws AlipayApiException {

        BigDecimal totalAmount = new BigDecimal(0);//总价格
        //保存交易名称（如果多个订单保存所有订单号，下划线分割）
        StringBuilder subject = new StringBuilder();
        //支付宝请求参数，多个商品同时结算默认traceNo为最后一个订单的id
        AliPay aliPay = new AliPay();
        //订单
        Orders orders = new Orders();

        for (OrderVo orderVo : orderVos) {
            ConfirmOrder confirmOrder = redisCache.getCacheObject(orderVo.getConfirmOrderId());
            if (confirmOrder == null) {
                throw new AlipayException("该订单已超时，请重新下单");
            }
            Integer buyNumber = confirmOrder.getNumber();//购买数量
            Integer amount = confirmOrder.getAmount();//剩余库存

            //库存不够
            if (buyNumber > amount) {
                throw new AlipayException("商品【" + confirmOrder.getGoodsName() + "】库存不足，请修改后再结算");
            }
            BigDecimal price = confirmOrder.getPrice();//价格

            //锁库存
            Goods newGoods = new Goods();
            newGoods.setId(confirmOrder.getGoodsId());
            newGoods.setNumber(amount - buyNumber);
            goodsService.updateById(newGoods);

            //创建未支付订单
            orders.setId(sequence.nextId() + "");
            orders.setGoodsId(confirmOrder.getGoodsId());
            orders.setCustomerId(confirmOrder.getSellerId());
            orders.setStatus(0);
            orders.setNumber(buyNumber);
            orders.setMoney(price.multiply(new BigDecimal(buyNumber)));
            orders.setRemark(orderVo.getRemark());
            orders.setAddress(orderVo.getAddress());
            orders.setPhone(orderVo.getPhone());
            //放入数据库
            orderService.save(orders);

            //移除购物车对应的商品
            shopcarService.removeById(confirmOrder.getShopcarId());

            //加总金额
            totalAmount = totalAmount.add(orders.getMoney());
            //放入rabbitMq延时队列
            //方法抛出异常后，已经进入消息队列的订单查询出来是空，直接在消费消息后return
            rabbitTemplate.convertAndSend(
                    RabbitConstant.DELAYED_EXCHANGE,
                    RabbitConstant.DELAYED_ROUTING_KEY,
                    orders.getId(),
                    new CorrelationData(orders.getId()));
            log.info("订单{}创建，进入队列", orders.getId());
            subject.append(orders.getId()).append("_");
        }
        //删除最后一个“_”
        subject.deleteCharAt(subject.lastIndexOf("_"));
        //调用支付宝支付接口
        aliPay.setTraceNo(orders.getId());
        aliPay.setTotalAmount(totalAmount.doubleValue());
        aliPay.setSubject(subject.toString());
        return aliPayService.pay(aliPay);
    }

    @Override
    public Map<String, Object> getMySoldList(String userId, Integer current, Integer limit) {
        Page<OrderListDto> page = new Page<>();
        baseMapper.selectMySold(page,userId);
        return BeanUtils.beanToPageMap(page);
    }

    @Override
    public List<ConfirmOrder> createConfirmOrder(ConfirmOrderVo[] confirmOrderVos) {
        //存放查出的所有确认单
        List<ConfirmOrder> list = new LinkedList<>();

        //查询每个要支付的单的确认单
        for (ConfirmOrderVo confirmOrderVo : confirmOrderVos) {
            String shopcarId = confirmOrderVo.getShopcarId();
            Integer number = confirmOrderVo.getNumber();
            ConfirmOrder confirmOrder = shopcarService.selectShopcarDetailById(shopcarId);
            confirmOrder.setId(String.valueOf(sequence.nextId()));
            confirmOrder.setNumber(number);
            confirmOrder.setShopcarId(shopcarId);
            list.add(confirmOrder);
            //将确定单对象放入缓存，解决直接在购物车修改商品数量然后结算的问题
            redisCache.setCacheObject(confirmOrder.getId(), confirmOrder, 30, TimeUnit.MINUTES);
        }
        return list;
    }

    @Override
    public OrderDetailDto getOrderDetail(String orderId) {
        return baseMapper.selectOrderDetail(orderId);
    }

    @Override
    public Map<String, Object> getMyBuyList(String userId, Integer current, Integer limit) {
        Page<OrderListDto> page = new Page<>();
        page = baseMapper.selectMyBuy(page,userId);
        return BeanUtils.beanToPageMap(page);
    }


}
