package com.example.hufs.domain.fridgeContent.dto.response;

import com.example.hufs.domain.food.entity.enumtype.StorageMethod;
import lombok.Builder;

import java.util.List;

@Builder
public record FridgeContentResponseDto(
        Long fridgeContentId,
        StorageMethod storageMethod,
        List<List<String>> foods
) {
}
