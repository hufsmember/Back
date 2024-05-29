package com.example.hufs.domain.fridgeContent.dto.response;

import lombok.Builder;

@Builder
public record FridgeContentInfoResponseDto(
        Integer refridgeTemp,
        Integer freezeTemp
) {
}
