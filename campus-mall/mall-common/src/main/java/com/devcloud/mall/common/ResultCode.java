package com.devcloud.mall.common;

/**
 * @author 吴员外
 * @date 2022/9/17 15:28
 *
 *  返回结果状态码
 */
public class ResultCode {

    /**
     * 成功状态码
     */
    public static final Integer SUCCESS = 20000;

    /**
     * 失败状态码
     */
    public static final Integer FAIL = 20001;

    /**
     * Token不合法状态码
     */
    public static final Integer JWT_ILLEGAL = 20002;

    /**
     * 支付失败状态码
     */
    public static final Integer PAY_FAIL = 20003;


}
