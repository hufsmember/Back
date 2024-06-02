package com.example.hufs.common.security.jwt;

import com.example.hufs.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class TokenManager {

    private final MemberService memberService;
    private final Set<String> tokenBlacklist = new HashSet<>();

    // 필요한 다른 의존성 주입

    // 다른 메서드들도 필요에 따라 추가할 수 있음

    public void addToBlacklist(String token) {
        tokenBlacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }
}