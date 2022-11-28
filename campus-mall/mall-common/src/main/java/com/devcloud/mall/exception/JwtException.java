package com.devcloud.mall.exception;

/**
 * @author 吴员外
 * @date 2022/11/25 17:49
 */
public class JwtException extends RuntimeException {

    public JwtException() {
    }

    public JwtException(String message) {
        super(message);
    }


}
