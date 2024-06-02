package com.example.hufs.backtoai.service;

import com.example.hufs.backtoai.dto.response.IngredientsResponseDto;
import com.example.hufs.backtoai.dto.response.RecommendRecipeDetailResponseDto;
import com.example.hufs.backtoai.dto.response.RecommendRecipeResponseDto;
import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.member.entity.Member;
import com.example.hufs.domain.member.repository.MemberRepository;
import com.example.hufs.domain.recipe.entity.Recipe;
import com.example.hufs.domain.recipe.repository.RecipeRepository;
import com.example.hufs.domain.recipeIngrendient.entity.RecipeIngredient;
import com.example.hufs.domain.recipeIngrendient.repository.RecipeIngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;

    public RecommendRecipeResponseDto getRecommendRecipe(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        //AI로 api 요청하기
        //요청으로 받은 데이터(Json)를 내가 보기 좋게 변환하여 담는다.
        //리스트dto로 넣을 거 넣고, 전체 결과는 Recipe테이블에 save

        return null;
    }

    public RecommendRecipeDetailResponseDto getRecommendRecipeDetail(Long recipeId) {

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(()->new BaseException(ErrorCode.RECIPE_NOT_FOUND));

        List<RecipeIngredient> recipeIngredientList = recipeIngredientRepository.findByRecipe(recipe);

        List<IngredientsResponseDto> ingredients = recipeIngredientList.stream()
                .map(recipeIngredient -> IngredientsResponseDto.builder()
                        .ingredient(recipeIngredient.getFood().getFoodName())
                        .quantity(recipeIngredient.getQuantity())
                        .build())
                .toList();

        return RecommendRecipeDetailResponseDto.builder()
                .imageUrl(recipe.getRecipeImageUrl())
                .description(recipe.getDescription())
                .ingredients(ingredients)
                .build();
    }
}
