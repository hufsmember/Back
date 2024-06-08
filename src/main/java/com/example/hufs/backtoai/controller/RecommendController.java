package com.example.hufs.backtoai.controller;

import com.example.hufs.backtoai.dto.response.RecommendRecipeDetailResponseDto;
import com.example.hufs.backtoai.dto.response.RecommendRecipeResponseDto;
import com.example.hufs.backtoai.service.RecommendService;
import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.common.security.principal.MemberDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recipes/recommends")
@RequiredArgsConstructor
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping()
    public BaseResponseDTO<List<RecommendRecipeResponseDto>> getRecommendRecipe(
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        return BaseResponseDTO.okWithData(recommendService.getRecommendRecipe(memberDetail.getUsername()));
    }

    @GetMapping("/detail/{recipe_id}")
    public BaseResponseDTO<RecommendRecipeDetailResponseDto> getRecommendRecipeDetail(
            @PathVariable("recipe_id") Long id,
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        return BaseResponseDTO.okWithData(recommendService.getRecommendRecipeDetail(id));
    }
}
