package com.example.hufs.domain.food.controller;

import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.common.security.principal.MemberDetail;
import com.example.hufs.domain.food.dto.response.FoodDetailResponseDto;
import com.example.hufs.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/get/{food_id}/")
    public BaseResponseDTO<FoodDetailResponseDto> getFoodDetail(
            @PathVariable("food_id") Long foodId
    ) {
        MemberDetail memberDetail = (MemberDetail) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        String email = memberDetail.getUsername();
        return BaseResponseDTO.okWithData(foodService.getFoodDetail(foodId, email));
    }
}
