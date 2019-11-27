package com.sanjutou.shopping.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * jwt相关工具类。
 *
 * @author admin
 */
@Component
public class JwtUtil {

    /**
     * 密钥。
     */
    private static String secret;


    @Value("${my-config.token-secret}")
    public void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    private JwtUtil() {
    }

    /**
     * 获取用户id。
     *
     * @param token token
     * @return 用户id
     */
    public static Integer getCustomerIdFromToken(@NonNull String token) {
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT jwt = jwtVerifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        return map.get("customerId").asInt();
    }
}
