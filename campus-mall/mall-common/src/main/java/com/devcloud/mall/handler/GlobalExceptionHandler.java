package com.devcloud.mall.handler;

import com.devcloud.mall.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 吴员外
 * @date 2022/10/30 23:14
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler{

    @ExceptionHandler
    public R exceptionHandler(Exception e) {
        e.printStackTrace();
        return R.error().message(e.getMessage());
    }
}
