package com.devcloud.mall.utils;

import com.devcloud.mall.common.R;
import com.devcloud.mall.common.ResultCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 吴员外
 * @date 2022/11/26 11:11
 */
public class HttpResponseUtil {

    public static void error(HttpServletResponse response,Integer code,String msg) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json;charset=utf-8");
        response.setStatus(HttpStatus.OK.value());
        try {
            R r = R.ok().code(ResultCode.JWT_ILLEGAL).message(msg);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(r);
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
