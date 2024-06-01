package com.example.hufs.common.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 엔드포인트에 대해 CORS 허용
                .allowedOriginPatterns("*")  // 모든 오리진 허용
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // 허용할 HTTP 메서드 지정
                .allowedHeaders("*")      // 모든 헤더 허용
                .allowCredentials(true);    // JWT 사용을 위한 자격 증명 허용
    }
}
