package com.devcloud.mall.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author 吴员外
 * @date 2022/11/6 11:16
 */
@Data
public class AliPay {

    private String tradeNo;
    private Double totalAmount;
    private String subject;
    //private String alipayTradeNo;

    //支付表单的过期时间
    private Date expireTime;

}
