package com.example.hufs.domain.member.dto;

public record MemberRequestDto(
        String name,
        String email,
        String password
) {
}
