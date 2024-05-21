package com.example.hufs.domain.member.dto;

public record MemberLoginRequestDto(
        String email,
        String password
) {
}
