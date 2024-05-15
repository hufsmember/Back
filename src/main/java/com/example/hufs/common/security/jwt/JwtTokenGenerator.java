package com.example.hufs.common.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenGenerator {

    private final SecretKey key;
    @Value("${JWT_ACCESS_TOKEN_EXPIRED_TIME}")
    private Long accessExpiredTimeMills;

    public JwtTokenGenerator(@Value("${JWT_ACCESS_TOKEN_SECRET_KEY}") String secret) {
        this.key = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    /*
    Jwt의 구조: 헤더.페이로드.시그니처 이렇게 3부분으로 구성
    헤더: typ: 토큰의 타입, alg: 알고리즘 방식
    페이로드: 클레임(토큰에서 사용할 정보의 조각)들의 집합
    서명: 유효성 검증에 사용되는 암호화 코드
     */
    public String createJwtToken(String email) {
        return Jwts.builder()
                .claim("email", email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessExpiredTimeMills))
                .signWith(key)
                .compact();
    }

    public String getUserEmail(String token) {
        Claims claims = extractClaim(token);
        return claims.get("email", String.class);
    }

    private Claims extractClaim(String token) {
        return Jwts.parser()
                .verifyWith(key).build()
                .parseSignedClaims(token)
                .getPayload();
    }

}
