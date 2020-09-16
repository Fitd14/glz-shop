package com.smy.shop.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.time.Duration;
import java.util.Date;

public class JWTTokenUtil {

    // 签发者
    private static final String TOKEN_ISSURE = "viosay";
    // 私钥
    private static final Algorithm TOKEN_SECRET = Algorithm.HMAC256("8x*i9n(f0e)i7J&I6N^X5I%N");
    // token失效时间
    private static final Duration TOKEN_EXPIRATION = Duration.ofDays(7);
    // refreshToken失效时间
    private static final Duration REFRESH_TOKEN_EXPIRATION = Duration.ofDays(15);

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 根据token获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsernameFromToken(String token) {
        String[] str = token.split("-");
        JWTVerifier verifier = JWT.require(TOKEN_SECRET).withIssuer(TOKEN_ISSURE).build();
        // 解码JWT
        DecodedJWT jwt = verifier.verify(str[1]);
        // 获取主题(用户名)
        String username = jwt.getSubject();
        return username;
    }

    /**
     * 生成token
     *
     * @param
     * @return
     */
    public static String createTokenByUsername(String username) {
        // 主题(用户名)
        String subject = username;
        // 签发时间
        Date issuedAt = new Date();
        // 失效时间
        Date expiresAt = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION.toMillis());
        // 生成token
        String token = JWT.create().withIssuer(TOKEN_ISSURE).withSubject(subject).withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt).sign(TOKEN_SECRET);
        return token;
    }

    /**
     * 生成refreshToken
     *
     * @param
     * @return
     */
    public static String createRefreshTokenByUsername(String username) {
        // 主题(用户名)
        String subject = username;
        // 签发时间
        Date issuedAt = new Date();
        // 失效时间
        Date expiresAt = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION.toMillis());
        // 生成token
        String token = JWT.create().withIssuer(TOKEN_ISSURE).withSubject(subject).withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt).sign(TOKEN_SECRET);
        return token;
    }


}
