package com.example.hufs.domain.member.dto;

import com.example.hufs.domain.member.entity.enumtype.AgeGroup;

public record MemberAgeRequestDto(
        AgeGroup ageGroup
) {
}
