package com.example.hufs.domain.fridgeContent.dto.response;

import lombok.Builder;

@Builder
public record FridgeContentInfoResponseDto(
        Long fridgeContentId,
        Integer refridgeTemp,
        Integer freezeTemp
) {
}
