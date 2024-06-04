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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendService {

    private final RecipeRepository recipeRepository;
    private final MemberRepository memberRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final WebClient webClient;

    public RecommendRecipeResponseDto getRecommendRecipe(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        //AI로 api 요청하기
        String url = "배포 url/api/recommend/recipe";

        //요청받는 데이터 클래스를 따로 만들어줘야함
        Mono<RecommendRecipeResponseDto> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("member_id",member.getId())
                        .build())
                .retrieve()
                .bodyToMono(RecommendRecipeResponseDto.class);
        //요청으로 받은 데이터(Json)를 내가 보기 좋게 변환하여 담는다.
        RecommendRecipeResponseDto recommendRecipeResponseDto = responseMono.block();

//        if (recommendRecipeResponseDto != null) {
//            List<Recipe> recipes = recommendRecipeResponseDto.getRecipes().stream().map(dto -> {
//                Recipe recipe = new Recipe();
//                recipe.setTitle(dto.getTitle());
//                recipe.setDescription(dto.getDescription());
//                recipe.setIngredients(dto.getIngredients().stream().map(ingredientDto -> {
//                    RecipeIngredient ingredient = new RecipeIngredient();
//                    ingredient.setName(ingredientDto.getName());
//                    ingredient.setQuantity(ingredientDto.getQuantity());
//                    return ingredient;
//                }).collect(Collectors.toList()));
//                // 필요한 다른 필드들 설정
//                return recipe;
//            }).collect(Collectors.toList());

//        리스트 dto로 넣을 거 넣고, 전체 결과는 Recipe 테이블에 save
//            recipeRepository.saveAll(recipes);


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
