package com.example.hufs.domain.member.dto.response;

import lombok.Builder;

@Builder
public record MemberPreferredRequestDto(
        String preferredCuisine,
        String nonPreferredCuisine,
        String preferredIngredient,
        String nonPreferredIngredient
) {
}
