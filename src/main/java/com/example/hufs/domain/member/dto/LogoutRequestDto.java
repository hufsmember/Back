package com.example.hufs.domain.member.dto;

import lombok.Builder;

@Builder
public record LogoutRequestDto(
        String token
) {
}
