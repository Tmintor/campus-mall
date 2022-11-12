package com.devcloud.mall.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 吴员外
 * @date 2022/11/6 10:50
 */
@Component
@ConfigurationProperties("alipay")
public class AliPayConstant {
    public static String APP_ID;
    public static String APP_PRIVATE_KEY;
    public static String ALIPAY_PUBLIC_KEY;
    public static String NOTIFY_URL;
    public static String GATEWAY_URL;

    public void setAppId(String appId) {
        AliPayConstant.APP_ID = appId;
    }

    public void setAppPrivateKey(String appPrivateKey) {
        AliPayConstant.APP_PRIVATE_KEY = appPrivateKey;
    }

    public void setAlipayPublicKey(String alipayPublicKey) {
        AliPayConstant.ALIPAY_PUBLIC_KEY = alipayPublicKey;
    }

    public void setNotifyUrl(String notifyUrl) {
        AliPayConstant.NOTIFY_URL = notifyUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        AliPayConstant.GATEWAY_URL = gatewayUrl;
    }
}
