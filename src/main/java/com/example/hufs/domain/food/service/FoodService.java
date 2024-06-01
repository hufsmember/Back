package com.example.hufs.domain.food.service;


import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.food.dto.response.FoodDetailResponseDto;
import com.example.hufs.domain.food.entity.Food;
import com.example.hufs.domain.food.repository.FoodRepository;
import com.example.hufs.domain.fridgeContent.entity.FridgeContent;
import com.example.hufs.domain.fridgeContent.repository.FridgeContentRepository;
import com.example.hufs.domain.member.entity.Member;
import com.example.hufs.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class FoodService {

    private final FoodRepository foodRepository;
    private final FridgeContentRepository fridgeContentRepository;
    private final MemberRepository memberRepository;

    public FoodDetailResponseDto getFoodDetail(Long foodId, String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        Food food = foodRepository.findById(foodId).orElseThrow(
                () -> new BaseException(ErrorCode.FOOD_NOT_FOUND));

        FridgeContent fridgeContent = fridgeContentRepository.findByFoodIdAndMemberId(foodId, member.getId())
                .orElseThrow(()->new BaseException(ErrorCode.FRIDGE_NOT_FOUND_MEMBER_FOOD));

        return FoodDetailResponseDto.builder()
                .foodName(food.getFoodName())
                .daysRemaining(fridgeContent.getDaysRemaining())
                .expiryDate(fridgeContent.getExpiryDate().toLocalDate())
                .imageUrl(food.getFood_image_url())
                .storageMethod(food.getStorageMethod())
                .quantity(fridgeContent.getQuantity())
                .purchaseDate(fridgeContent.getPurchaseDate().toLocalDate())
                .build();

    }
}
