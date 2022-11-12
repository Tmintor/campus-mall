package com.devcloud.mall.domain;

import lombok.Data;

/**
 * @author 吴员外
 * @date 2022/11/6 11:16
 */
@Data
public class AliPay {

    private String traceNo;
    private Double totalAmount;
    private String subject;
    private String alipayTraceNo;

}
