package com.example.hufs.common.config;

import com.example.hufs.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final MemberRepository memberRepository;
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") // cors를 적용할 spring서버의 url 패턴.
                .allowedOrigins("http://43.200.170.84/", "http://192.168.219.122:8081"
                , "http://192.168.35.48:8081") // cors를 허용할 도메인. 제한을 모두 해제하려면 "**"
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true); // cors를 허용할 method
    }
}
