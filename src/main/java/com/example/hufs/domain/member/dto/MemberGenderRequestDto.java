package com.example.hufs.domain.member.dto;

import com.example.hufs.domain.member.entity.enumtype.Gender;
import jakarta.validation.constraints.NotBlank;

public record MemberGenderRequestDto(
        @NotBlank
        Gender gender
) {
}
