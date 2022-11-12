package com.devcloud.mall.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 吴员外
 * @date 2022/9/21 20:21
 */
@Component
@ConfigurationProperties("aliyun.oss")
public class AliyunConstant{


    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    public void setEndPoint(String endPoint) {
        AliyunConstant.END_POINT = endPoint;
    }

    public void setAccessKeyId(String accessKeyId) {
        AliyunConstant.ACCESS_KEY_ID = accessKeyId;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        AliyunConstant.ACCESS_KEY_SECRET = accessKeySecret;
    }

    public void setBucketName(String bucketName) {
        AliyunConstant.BUCKET_NAME = bucketName;
    }
}
