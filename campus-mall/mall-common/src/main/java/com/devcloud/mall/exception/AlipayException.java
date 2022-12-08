package com.devcloud.mall.exception;

/**
 * @author 吴员外
 * @date 2022/12/2 20:39
 */
public class AlipayException extends RuntimeException {

    public AlipayException() {
        super();
    }

    public AlipayException(String message) {
        super(message);
    }
}
