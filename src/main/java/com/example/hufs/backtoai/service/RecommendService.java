package com.example.hufs.backtoai.service;

import com.example.hufs.backtoai.dto.response.IngredientsResponseDto;
import com.example.hufs.backtoai.dto.response.RecommendRecipeAIDto;
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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final WebClient webClient;

    public List<RecommendRecipeResponseDto> getRecommendRecipe(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        String endPoint = "/api/recommend/recipe";

        //요청받는 데이터 클래스를 따로 만들어줘야함
        Mono<List<RecommendRecipeAIDto>> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(endPoint)
                        .queryParam("member_id",member.getId())
                        .build())
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> Mono.error(new BaseException(ErrorCode.API_REQUEST_FAIL)))
                .bodyToFlux(RecommendRecipeAIDto.class)
                .collectList();

       List<RecommendRecipeAIDto> recipeAIDtos = responseMono.block();

       if(recipeAIDtos.isEmpty()) {
           throw new BaseException(ErrorCode.API_REQUEST_NULL);
       }

       List<Recipe> recipes = recipeAIDtos.stream()
               .map(recipeAIDto -> Recipe.builder()
                       .recipeName(recipeAIDto.recipeName())
                       .cuisineType(recipeAIDto.cuisineType())
                       .recipeImageUrl(recipeAIDto.imageUrl())
                       .isVegan(recipeAIDto.isVegan())
                       .description(recipeAIDto.description())
                       .build())
               .toList();

       List<Recipe> savedRecipes = recipeRepository.saveAll(recipes);

        return savedRecipes.stream().map(
                savedRecipe -> RecommendRecipeResponseDto.builder()
                        .recipeId(savedRecipe.getId())
                        .cuisineName(savedRecipe.getRecipeName())
                        .imageUrl(savedRecipe.getRecipeImageUrl())
                        .build())
                .collect(Collectors.toList());
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
