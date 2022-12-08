package com.devcloud.mall.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.devcloud.mall.constant.AliPayConstant;
import com.devcloud.mall.domain.AliPay;
import com.devcloud.mall.domain.Orders;
import com.devcloud.mall.domain.Tran;
import com.devcloud.mall.mapper.OrderMapper;
import com.devcloud.mall.mapper.TranMapper;
import com.devcloud.mall.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 吴员外
 * @date 2022/11/6 11:20
 */
@Service
public class AliPayServiceImpl implements AliPayService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private TranMapper tranMapper;

    @Override
    public String pay(AliPay aliPay) throws AlipayApiException{
        // 1. 创建Client，通用SDK提供的Client，负责调用支付宝的API
        AlipayClient alipayClient = new DefaultAlipayClient(
                AliPayConstant.GATEWAY_URL,
                AliPayConstant.APP_ID,
                AliPayConstant.APP_PRIVATE_KEY,
                "JSON",
                "UTF-8",
                AliPayConstant.ALIPAY_PUBLIC_KEY,
                "RSA2");

        // 2. 创建 Request并设置Request参数
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();  // 发送请求的 Request类
        request.setNotifyUrl(AliPayConstant.NOTIFY_URL);
        JSONObject bizContent = new JSONObject();
        bizContent.set("out_trade_no", aliPay.getTraceNo());  // 我们自己生成的订单编号
        bizContent.set("total_amount", aliPay.getTotalAmount()); // 订单的总金额
        bizContent.set("subject", aliPay.getSubject());   // 支付的名称
        bizContent.set("product_code", "FAST_INSTANT_TRADE_PAY");  // 固定配置
        request.setBizContent(bizContent.toString());

        // 执行请求，拿到响应的结果，返回给浏览器
        String form = "";
        try {
            form = alipayClient.pageExecute(request).getBody(); // 调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw e;
        }
        return form;
    }

    @Override
    @Transactional
    public void payNotify(HttpServletRequest request) throws AlipayApiException {

        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            //         =========支付宝异步回调========"
            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
            }

            String sign = params.get("sign");
            String content = AlipaySignature.getSignCheckContentV1(params);
            boolean checkSignature = AlipaySignature.rsa256CheckContent(content, sign, AliPayConstant.ALIPAY_PUBLIC_KEY, "UTF-8"); // 验证签名
            // 支付宝验签
            if (checkSignature) {
                // 验签通过
                /*System.out.println("交易名称: " + params.get("subject"));
                System.out.println("交易状态: " + params.get("trade_status"));
                System.out.println("支付宝交易凭证号: " + params.get("trade_no"));
                System.out.println("商户订单号: " + params.get("out_trade_no"));
                System.out.println("交易金额: " + params.get("total_amount"));
                System.out.println("买家在支付宝唯一id: " + params.get("buyer_id"));
                System.out.println("买家付款时间: " + params.get("gmt_payment"));
                System.out.println("买家付款金额: " + params.get("buyer_pay_amount"));*/

                // 更新订单
                Orders orders = new Orders();
                String subject = params.get("subject");
                String[] orderIds = subject.split("_");

                for (String orderId : orderIds) {
                    orders.setId(orderId);
                    orders.setStatus(1);
                    orderMapper.updateById(orders);
                }

                //创建交易记录
                Tran tran = new Tran();
                tran.setTradeState(params.get("trade_status"))
                        .setTransactionId(params.get("trade_no"))
                        .setOrderId(params.get("out_trade_no"))
                        .setSubject(subject)
                        .setTotalAmount(new BigDecimal(params.get("total_amount")))
                        .setCreateTime(DateUtil.parse(params.get("gmt_payment")))
                        .setUpdateTime(DateUtil.parse(params.get("gmt_payment")));
                tranMapper.insert(tran);
            }
        }
    }
}
