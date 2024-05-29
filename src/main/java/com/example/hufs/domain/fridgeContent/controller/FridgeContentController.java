package com.example.hufs.domain.fridgeContent.controller;

import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.common.security.principal.MemberDetail;
import com.example.hufs.domain.fridgeContent.dto.response.FridgeContentInfoResponseDto;
import com.example.hufs.domain.fridgeContent.service.FridgeContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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

}
