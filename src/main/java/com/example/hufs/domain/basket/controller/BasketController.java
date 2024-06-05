package com.example.hufs.domain.basket.controller;

import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.common.security.principal.MemberDetail;
import com.example.hufs.domain.basket.dto.BasketRequestDto;
import com.example.hufs.domain.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @PostMapping()
    public BaseResponseDTO<Void> saveBasket(
            @RequestBody List<BasketRequestDto> request,
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        basketService.saveBasket(request, memberDetail.getUsername());
        return BaseResponseDTO.ok();
    }
}
