package com.sanjutou.shopping.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sanjutou.shopping.config.CheckLogin;
import com.sanjutou.shopping.config.PassToken;
import com.sanjutou.shopping.entity.Customer;
import com.sanjutou.shopping.service.CustomerService;
import com.sanjutou.shopping.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    /**
     * customerService.
     */
    @Autowired
    private CustomerService customerService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws IOException {
        boolean result = true;
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passToken注释，有则跳过认证
        if (!method.isAnnotationPresent(PassToken.class)) {
            // 执行认证
            if (token == null) {
                result = false;
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token为空");
            } else {
                try {
                    // 验证 token
                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
                    DecodedJWT jwt = jwtVerifier.verify(token);
                    // 判断是否过期
                    if (jwt.getExpiresAt().before(new Date())) {
                        result = false;
                        httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "token过期");
                    }
                } catch (JWTDecodeException j) {
                    result = false;
                    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "token校验失败");
                }
            }
        }
        // 注解checkLogin,没有则不校验
        if (method.isAnnotationPresent(CheckLogin.class)) {
            if (token == null) {
                result = false;
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "token为空");
            } else {
                try {
                    final Integer id = JwtUtil.getCustomerIdFromToken(token);
                    final Customer customer = customerService.getById(id);
                    if (customer == null) {
                        result = false;
                        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "无效的用户");
                    }
                } catch (JWTDecodeException e) {
                    result = false;
                    httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "token校验失败");
                }
            }
        }
        return result;
    }
}
