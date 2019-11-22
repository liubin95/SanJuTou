package com.sanjutou.shopping.controller;

import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获。
 *
 * @author admin
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 全局异常捕获。
     *
     * @param exception 异常。
     * @return re
     */
    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception exception) {
        final Result<Object> result = new Result<>();
        LOGGER.error("ApiException 异常抛出：{}", exception);
        result.setCodeMsg(Messages.E0001);
        return result;
    }
}
