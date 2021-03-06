package com.sanjutou.shopping.config;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sanjutou.shopping.dictionary.Messages;
import com.sanjutou.shopping.entity.result.Result;

/**
 * 全局异常捕获。
 *
 * @author liubin
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 单个参数校验异常。
     *
     * @param exception ConstraintViolationException
     * @return re
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException exception) {
        final Result result = new Result();
        LOGGER.error("参数校验失败：{}", exception);
        result.setCodeMsg(Messages.E0002);
        return result;
    }

    /**
     * 实体参数校验失败异常。
     *
     * @param exception ValidatedException
     * @return re
     */
    @ExceptionHandler(ValidatedException.class)
    public Result handleValidatedException(ValidatedException exception) {
        final Result result = new Result();
        LOGGER.error("参数校验失败：{}", exception);
        result.setCodeMsg(Messages.E0002);
        return result;
    }

    /**
     * 全局异常捕获。
     *
     * @param exception 异常。
     * @return re
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception exception) {
        final Result result = new Result();
        LOGGER.error("ApiException 异常抛出：{}", exception);
        result.setCodeMsg(Messages.E0001);
        return result;
    }

}
