package com.example.hufs.domain.member.service;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.common.security.jwt.JwtTokenGenerator;
import com.example.hufs.domain.allergy.repository.AllergyRepository;
import com.example.hufs.domain.member.dto.MemberGenderRequestDto;
import com.example.hufs.domain.member.dto.MemberLoginRequestDto;
import com.example.hufs.domain.member.dto.MemberRequestDto;
import com.example.hufs.domain.member.entity.Member;
import com.example.hufs.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final AllergyRepository allergyRepository;

    public MemberLoginRequestDto register(MemberRequestDto memberRequestDto, Boolean isFamilyExist) {
        // 1. 유효성 검사
        if ((memberRepository.existsByEmail(memberRequestDto.email()))) {
            throw new BaseException(ErrorCode.DUPLICATED_MEMBER);
        }
        // 2.비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(memberRequestDto.password());

        // 3 멤버로 변환
        Member member = Member.builder()
                .userName(memberRequestDto.name())
                .email(memberRequestDto.email())
                .password(encodedPassword)
                .isFamilyExist(isFamilyExist)
                .termAgreed(memberRequestDto.termAgreed())
                .build();

        // 4. db에 저장
        memberRepository.save(member);

        return new MemberLoginRequestDto(memberRequestDto.email(), memberRequestDto.password());
    }

    public void gender(MemberGenderRequestDto requestDto, String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        member.setGender(requestDto.gender());
    }

    //로그인 요청이 들어오면 내부로직으로 검증 후 토큰을 만들고 그 토큰을 전달해주어야 함.
    @Transactional(readOnly = true)
    public String login(MemberLoginRequestDto memberLoginRequestDto) {
        Member member = memberRepository.findByEmail(memberLoginRequestDto.email())
                .orElseThrow(
                        () -> new BaseException(ErrorCode.MEMBER_NOT_EXIST)
                );
        if (!(passwordMatcher(memberLoginRequestDto.password(), member))) {
            throw new BaseException(ErrorCode.MEMBER_NOT_EXIST);
        }
        return jwtTokenGenerator.createJwtToken(memberLoginRequestDto.email());
    }

    private Boolean passwordMatcher(final String requestPassword, final Member member) {
        return passwordEncoder.matches(requestPassword, member.getPassword());
    }
}
