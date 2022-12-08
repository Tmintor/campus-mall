package com.devcloud.mall.handler;

import com.alipay.api.AlipayApiException;
import com.devcloud.mall.common.R;
import com.devcloud.mall.common.ResultCode;
import com.devcloud.mall.exception.AlipayException;
import com.devcloud.mall.exception.JwtException;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 吴员外
 * @date 2022/10/30 23:14
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @Order(1)
    @ExceptionHandler(JwtException.class)
    public R jwtExceptionHandler(JwtException jwtException) {
        return R.error().code(ResultCode.JWT_ILLEGAL).message(jwtException.getMessage());
    }

    @Order(1)
    @ExceptionHandler(AlipayApiException.class)
    public R alipayApiExceptionHandler(AlipayApiException alipayApiException) {
        return R.error().code(ResultCode.PAY_FAIL).message("请求支付失败，请稍后再试");
    }

    @Order(1)
    @ExceptionHandler(AlipayException.class)
    public R alipayExceptionHandler(AlipayException alipayException) {
        return R.error().code(ResultCode.PAY_FAIL).message(alipayException.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public R exceptionHandler(Exception e) {
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }
}
