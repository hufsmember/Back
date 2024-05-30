package com.example.hufs.domain.fridgeContent.service;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.food.entity.Food;
import com.example.hufs.domain.food.entity.enumtype.StorageMethod;
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
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FridgeContentService {

    private final FridgeContentRepository fridgeContentRepository;
    private final MemberRepository memberRepository;

    public FridgeContentInfoResponseDto getFridgeContentInfo(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        FridgeContent fridgeContent = fridgeContentRepository.findByMember(member)
                .orElseThrow(() -> new BaseException(ErrorCode.FRIDGE_NOT_EXIST));

        return FridgeContentInfoResponseDto.builder()
                .fridgeContentId(fridgeContent.getId())
                .refridgeTemp(fridgeContent.getRefridgeTemp())
                .freezeTemp(fridgeContent.getFreezeTemp())
                .build();
    }

    public FridgeContentResponseDto getContent(Long fridgeContentId,
                                               StorageMethod storageMethod) {
        FridgeContent fridgeContent = fridgeContentRepository.findById(fridgeContentId)
                .orElseThrow(() -> new BaseException(ErrorCode.FRIDGE_NOT_EXIST));

        if (fridgeContent.getFoods().isEmpty()) {
            throw new BaseException(ErrorCode.FRIDGE_IS_EMPTY);
        }

        List<List<String>> foods = new ArrayList<>();

        for (Food food : fridgeContent.getFoods()) {
            if (food.getStorageMethod().equals(storageMethod.getType())) {
                foods.add(Arrays.asList(food.getFoodName(), food.getFood_image_url()));
            }
        }

        return FridgeContentResponseDto.builder()
                .fridgeContentId(fridgeContentId)
                .storageMethod(storageMethod.getType())
                .foods(foods)
                .build();
    }
}
