package com.example.hufs.domain.fridgeContent.service;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.fridgeContent.dto.response.FridgeContentInfoResponseDto;
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
public class FridgeContentService {

    private final FridgeContentRepository fridgeContentRepository;
    private final MemberRepository memberRepository;

    public FridgeContentInfoResponseDto getFridgeContentInfo(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        FridgeContent fridgeContent = fridgeContentRepository.findByMember(member)
                .orElseThrow(()->new BaseException(ErrorCode.FRIDGE_NOT_EXIST));

        return FridgeContentInfoResponseDto.builder()
                .refridgeTemp(fridgeContent.getRefridgeTemp())
                .freezeTemp(fridgeContent.getFreezeTemp())
                .build();
    }
}
