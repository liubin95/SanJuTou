package com.sanjutou.shopping.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sanjutou.shopping.config.PassToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * token拦截器。
 *
 * @author liubin
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    /**
     * 密钥。
     */
    @Value("${my-config.token-secret}")
    private String secret;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) {
        boolean result = true;
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passToken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (!passToken.required()) {
                result = false;
            }
        } else {
            // 执行认证
            if (token == null) {
                result = false;
            } else {
                try {
                    // 验证 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
                    DecodedJWT jwt = jwtVerifier.verify(token);
                    // 判断是否过期
                    if (jwt.getExpiresAt().before(new Date())) {
                        result = false;
                    }
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
            }
        }
        return result;
    }
}
