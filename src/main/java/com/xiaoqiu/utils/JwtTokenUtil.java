package com.xiaoqiu.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author xiaoqiu
 * @date 2025/3/12 21:12
 * @description JWT 工具类
 */
@Component
public class JwtTokenUtil {

    // 原始密钥字符串（32位以上字符）
    private static final String secretString = "xiaoqiu_blog_backend_secret_key_123456";

    // 通过 Keys 工具生成 SecretKey
    private final SecretKey secretKey = Keys.hmacShaKeyFor(secretString.getBytes(StandardCharsets.UTF_8));

    private final long expiration =  24 * 60 * 60 * 1000; // 1 天

    /**
     * 生成 Token
     */
    public String generateToken(Long userId) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))               // 载荷
                .setIssuedAt(new Date())                          // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))  // 过期时间
                .signWith(secretKey, SignatureAlgorithm.HS256)    // 签名算法 + 密钥
                .compact();
    }

    /**
     * 解析 Token，返回用户ID
     */
    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

    }

    public String refreshToken(String token) {

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + expiration))
                    .signWith(secretKey, SignatureAlgorithm.HS256)
                    .compact();
    }

}
