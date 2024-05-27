package com.example.hufs.domain.member.controller;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.common.security.principal.MemberDetail;
import com.example.hufs.domain.member.dto.MemberGenderRequestDto;
import com.example.hufs.domain.member.dto.MemberLoginRequestDto;
import com.example.hufs.domain.member.dto.MemberRequestDto;
import com.example.hufs.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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

        System.out.println(memberDetail);

        memberService.gender(requestDto, memberDetail.getUsername());

        return BaseResponseDTO.ok();
    }
}
