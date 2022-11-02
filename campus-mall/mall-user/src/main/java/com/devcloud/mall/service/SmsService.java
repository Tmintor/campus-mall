package com.devcloud.mall.service;

/**
 * @author 吴员外
 * @date 2022/10/30 20:46
 */
public interface SmsService {

    boolean getVerificationCode(String phone);

}
