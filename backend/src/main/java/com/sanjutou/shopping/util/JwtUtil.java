package com.sanjutou.shopping.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;

import javax.servlet.http.HttpServletRequest;

public class JwtUtil {

    public static Integer getCustomerIdFromRequest(HttpServletRequest httpServletRequest) {
        Integer userId;
        try {
            userId = Integer.parseInt(JWT.decode(httpServletRequest.getHeader("token")).getAudience().get(0));
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        return userId;
    }
}
