package com.example.hufs.domain.fridgeContent.controller;

import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.common.security.principal.MemberDetail;
import com.example.hufs.domain.food.entity.enumtype.StorageMethod;
import com.example.hufs.domain.fridgeContent.dto.response.FridgeContentInfoResponseDto;
import com.example.hufs.domain.fridgeContent.dto.response.FridgeContentResponseDto;
import com.example.hufs.domain.fridgeContent.service.FridgeContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fridge/content")
public class FridgeContentController {

    private final FridgeContentService fridgeContentService;

    @GetMapping("/info")
    public BaseResponseDTO<FridgeContentInfoResponseDto> getInfo(
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        String email = memberDetail.getUsername();

        return BaseResponseDTO.okWithData(fridgeContentService.getFridgeContentInfo(email));
    }

    @GetMapping("/{fridge_content_id}/ingredients/{storage_method}/list")
    public BaseResponseDTO<FridgeContentResponseDto> getContent(
            @PathVariable("fridge_content_id") Long fridgeContentId,
            @PathVariable("storage_method") StorageMethod storageMethod,
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        return BaseResponseDTO.okWithData(fridgeContentService.getContent(fridgeContentId,
                storageMethod));
    }

}
