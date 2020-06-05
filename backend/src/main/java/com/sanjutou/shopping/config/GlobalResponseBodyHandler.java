package com.sanjutou.shopping.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.sanjutou.shopping.entity.result.Result;

/**
 * 处理解析 {@link ResponseBodyAdvice} 统一返回包装器
 *
 * @author admin
 */
@RestControllerAdvice
public class GlobalResponseBodyHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@Nullable MethodParameter returnType, @Nullable Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, @Nullable MethodParameter returnType, @Nullable MediaType selectedContentType, @Nullable Class<? extends HttpMessageConverter<?>> selectedConverterType, @Nullable ServerHttpRequest request, @Nullable ServerHttpResponse response) {
        //返回值为空
        if (body == null) {
            return new Result();
        }
        //返回值为result
        if (body instanceof Result) {
            return body;
        }
        if (body instanceof String) {
            final Result result = new Result();
            result.setObj(String.valueOf(body));
            return result;
        }
        final Result result = new Result();
        result.setObj(body);
        return result;
    }
}
