package com.example.hufs.member.domain.dto;

public record MemberLoginRequestDto(
        String email,
        String password
) {
}
