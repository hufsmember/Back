package com.example.hufs.domain.member.controller;

import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.domain.member.dto.MemberLoginRequestDto;
import com.example.hufs.domain.member.dto.MemberRequestDto;
import com.example.hufs.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberPublicController {

    private final MemberService memberService;

    @PostMapping("/check/register")
    public BaseResponseDTO<Void> register(
            @RequestParam Boolean isFamilyExist,
            @RequestBody MemberRequestDto memberRequestDto
            ){
        memberService.register(memberRequestDto, isFamilyExist);
        return BaseResponseDTO.ok();
    }

    @PostMapping("/login")
    public BaseResponseDTO<Void> login(
            HttpServletResponse response,
            @RequestBody MemberLoginRequestDto memberLoginRequestDto
            ){
        String token = memberService.login(memberLoginRequestDto);
        response.setHeader(HttpHeaders.AUTHORIZATION, token);
        return BaseResponseDTO.ok();
    }
}
