package com.xiaoqiu.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class JwtUtils {

    private static final String SECRET = ("abcdefghijklmnopqrstuvwxyz12345678987654321"); // 至少 32 位长度
    private static final long ACCESS_EXPIRE = 30 * 60 * 1000; // 30分钟
    private static final long REFRESH_EXPIRE = 7 * 24 * 60 * 60 * 1000; // 7天

    private static Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 生成 Access Token
     */
    public static String generateAccessToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRE))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 生成 Refresh Token
     */
    public static String generateRefreshToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRE))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析用户ID
     */
    public static Long getUserId(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return Long.parseLong(claims.getSubject());
        } catch (ExpiredJwtException e) {
            log.warn("Token 过期", e);
        } catch (JwtException e) {
            log.warn("Token 无效", e);
        }
        return null;
    }

    /**
     * 验证 token 是否有效
     */
    public static boolean isTokenValid(String token) {
        return getUserId(token) != null;
    }
}
