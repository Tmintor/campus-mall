package com.devcloud.mall.service;

import com.alipay.api.AlipayApiException;
import com.devcloud.mall.domain.AliPay;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 吴员外
 * @date 2022/11/6 11:19
 */
public interface AliPayService {

    String pay(AliPay aliPay);

    void payNotify(HttpServletRequest request) throws AlipayApiException;
}
