package com.example.hufs.domain.member.dto;

import java.util.Set;

public record MemberVeganAllergyRequestDto(
        Boolean IsVegan,
        Set<String> allergiesName
) {
}
