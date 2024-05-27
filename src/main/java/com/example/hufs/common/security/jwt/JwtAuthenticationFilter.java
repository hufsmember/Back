package com.example.hufs.common.security.jwt;

import com.example.hufs.common.security.principal.MemberDetailsProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenGenerator jwtTokenGenerator;
    private final MemberDetailsProvider memberDetailsProvider;

    // 사용자 인증을 처리
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain
    ) throws ServletException, IOException {
        //header에서 토큰 값을 읽어와야 함.
        String accessToken = request.getHeader(HttpHeaders.AUTHORIZATION); //header의 AUTHORIZATION 필드를 읽어옴.(해당 필드에는 토큰 값이 있음)
        if (accessToken!=null){
            Authentication authentication = getEmailPassword(accessToken.trim()); //받은 엑세스 토큰으로 사용자 인증 정보를 불러옴

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication); //이 인증 정보를 security 필터에 전달.
        }

        filterChain.doFilter(request, response); //Jwt 인증이 끝났으니 다음 필터로 이동
    }

    private Authentication getEmailPassword(String token) {
        String email = jwtTokenGenerator.getUserEmail(token);
        if (email!=null) {
            UserDetails userDetails = memberDetailsProvider.loadUserByUsername(email);
            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    authorities
            );
        }
        return null;
    }
}
