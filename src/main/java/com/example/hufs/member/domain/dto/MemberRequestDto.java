package com.example.hufs.member.domain.dto;

public record MemberRequestDto(
        String name,
        String email,
        String password
) {
}
