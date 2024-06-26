package com.example.hufs.domain.fridgeContent.service;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.food.entity.Food;
import com.example.hufs.domain.food.repository.FoodRepository;
import com.example.hufs.domain.fridgeContent.dto.response.FoodResponseDto;
import com.example.hufs.domain.fridgeContent.dto.response.FridgeContentInfoResponseDto;
import com.example.hufs.domain.fridgeContent.dto.response.FridgeContentResponseDto;
import com.example.hufs.domain.fridgeContent.entity.FridgeContent;
import com.example.hufs.domain.fridgeContent.repository.FridgeContentRepository;
import com.example.hufs.domain.member.entity.Member;
import com.example.hufs.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FridgeContentService {

    private final FridgeContentRepository fridgeContentRepository;
    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;

//    public FridgeContentInfoResponseDto getFridgeContentInfo(String email) {
//
//        Member member = memberRepository.findByEmail(email)
//                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_EXIST));
//
//        FridgeContent fridgeContent = fridgeContentRepository.findByMember(member)
//                .orElseThrow(() -> new BaseException(ErrorCode.FRIDGE_NOT_EXIST));
//
//        return FridgeContentInfoResponseDto.builder()
//                .fridgeContentId(fridgeContent.getId())
//                .refridgeTemp(fridgeContent.getRefridgeTemp())
//                .freezeTemp(fridgeContent.getFreezeTemp())
//                .build();
//    }

    public FridgeContentInfoResponseDto getFridgeContentInfo(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        FridgeContent fridgeContent = fridgeContentRepository.findByMemberId(member.getId())
                .orElseThrow(() -> new BaseException(ErrorCode.FRIDGE_NOT_EXIST));

                return FridgeContentInfoResponseDto.builder()
                .fridgeContentId(fridgeContent.getId())
                .refridgeTemp(fridgeContent.getRefridgeTemp())
                .freezeTemp(fridgeContent.getFreezeTemp())
                .build();
    }

//    public FridgeContentResponseDto getContent(Long fridgeContentId,
//                                               StorageMethod storageMethod) {
//        FridgeContent fridgeContent = fridgeContentRepository.findById(fridgeContentId)
//                .orElseThrow(() -> new BaseException(ErrorCode.FRIDGE_NOT_EXIST));
//
//        if (fridgeContent.getFoods().isEmpty()) {
//            throw new BaseException(ErrorCode.FRIDGE_IS_EMPTY);
//        }
//
//        List<FoodResponseDto> foods = new ArrayList<>();
//
//        for (Food food : fridgeContent.getFoods()) {
//            if (food.getStorageMethod().equals(storageMethod.getType())) {
//
//                FoodResponseDto foodToResponse = FoodResponseDto.builder()
//                        .foodId(food.getId())
//                        .foodName(food.getFoodName())
//                        .imageUrl(food.getFood_image_url())
//                        .build();
//
//                foods.add(foodToResponse);
//            }
//        }
//
//        return FridgeContentResponseDto.builder()
//                .fridgeContentId(fridgeContentId)
//                .storageMethod(storageMethod.getType())
//                .foods(foods)
//                .build();
//    }

    public FridgeContentResponseDto getContent(Long fridgeContentId,
                                               String storageMethod) {

        List<Long> foodIds = fridgeContentRepository.findFoodIdById(fridgeContentId);

        List<FoodResponseDto> foods = new ArrayList<>();

        for (Long foodId : foodIds) {
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(()->new BaseException(ErrorCode.FOOD_NOT_FOUND));

            if(!food.getStorageMethod().equals(storageMethod)) {
                continue;
            }

            FoodResponseDto foodToResponse = FoodResponseDto.builder()
                        .foodId(food.getId())
                        .foodName(food.getFoodName())
                        .imageUrl(food.getFood_image_url())
                        .build();

            foods.add(foodToResponse);
        }
        return FridgeContentResponseDto.builder()
                .fridgeContentId(fridgeContentId)
                .storageMethod(storageMethod)
                .foods(foods)
                .build();
    }

}
