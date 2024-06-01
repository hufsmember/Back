package com.example.hufs.domain.member.dto.response;

import lombok.Builder;

@Builder
public record MemberResponseDto(
        String memberName
) {
}
