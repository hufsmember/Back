package com.example.hufs.domain.member.controller;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.common.security.principal.MemberDetail;
import com.example.hufs.domain.member.dto.*;
import com.example.hufs.domain.member.dto.response.MemberResponseDto;
import com.example.hufs.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/check/register")
    public BaseResponseDTO<Void> register(
            HttpServletResponse response,
            @RequestParam Boolean isFamilyExist,
            @RequestBody MemberRequestDto memberRequestDto
    ){
        MemberLoginRequestDto requestDto = memberService.register(memberRequestDto, isFamilyExist);

        String token = memberService.login(requestDto);
        response.setHeader("accessToken", token);

        return BaseResponseDTO.ok();
    }

    @PostMapping("/login")
    public BaseResponseDTO<Void> login(
            HttpServletResponse response,
            @RequestBody MemberLoginRequestDto memberLoginRequestDto
            ){
        String token = memberService.login(memberLoginRequestDto);
        response.setHeader("accessToken", token);

        return BaseResponseDTO.ok();
    }

    @PostMapping("/gender")
    public BaseResponseDTO<Void> gender(
            @RequestBody @Valid MemberGenderRequestDto requestDto,
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        if (memberDetail == null) {
            throw new BaseException(ErrorCode.TOKEN_NOT_FOUND);
        }

        memberService.gender(requestDto, memberDetail.getUsername());
        return BaseResponseDTO.ok();
    }

    @PostMapping("/age-group")
    public BaseResponseDTO<Void> age(
            @RequestBody @Valid MemberAgeRequestDto requestDto,
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        if (memberDetail == null) {
            throw new BaseException(ErrorCode.TOKEN_NOT_FOUND);
        }

        memberService.age(requestDto, memberDetail.getUsername());
        return BaseResponseDTO.ok();
    }

    @PostMapping("/vegan/allergies/type")
    public BaseResponseDTO<Void> veganAndAllergy(
            @RequestBody @Valid MemberVeganAllergyRequestDto requestDto,
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        if (memberDetail == null) {
            throw new BaseException(ErrorCode.TOKEN_NOT_FOUND);
        }

        memberService.veganAndAllergy(requestDto, memberDetail.getUsername());
        return BaseResponseDTO.ok();
    }

    @GetMapping("/get/{member_id}")
    public BaseResponseDTO<MemberResponseDto> getMember(
            @PathVariable("member_id") Long id
    ) {
        return BaseResponseDTO.okWithData(memberService.getMember(id));
    }
}
