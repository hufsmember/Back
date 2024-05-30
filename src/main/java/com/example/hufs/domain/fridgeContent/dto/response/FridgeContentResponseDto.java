package com.example.hufs.domain.fridgeContent.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record FridgeContentResponseDto(
        Long fridgeContentId,
        String storageMethod,
        List<List<String>> foods
) {
}
