package com.devcloud.mall.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.devcloud.mall.service.SmsService;
import com.devcloud.mall.utils.HttpUtils;
import com.devcloud.mall.utils.RedisCache;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 吴员外
 * @date 2022/10/30 20:46
 */
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private RedisCache redisCache;

    @Override
    public boolean getVerificationCode(String phone) {

        String host = "https://dfsns.market.alicloudapi.com";
        String path = "/data/send_sms";
        String method = "POST";
        String appcode = "d2c2ea0abd724c188cc254b5ff8ec726";
        Map<String, String> headers = new HashMap<>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();

        //生成六位验证码
        String code = RandomUtil.randomNumbers(6);
        bodys.put("content", "code:" + code);
        bodys.put("phone_number", phone);
        bodys.put("template_id", "TPL_0000");

        try {
            HttpUtils.doPost(host, path, method, headers, querys, bodys);
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            //发送成功，验证码放入redis

            redisCache.setCacheObject(phone, code, 30, TimeUnit.MINUTES);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
