package com.sanjutou.shopping.config;

/**
 * 参数校验自定义异常。
 *
 * @author liubin
 */
public class ValidatedException extends Exception {

    public ValidatedException(String message) {
        super(message);
    }
}
