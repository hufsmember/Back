package com.example.hufs.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
빈 등록 등 단순 설정을 모아두는 클래스
 */
@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//null값이 포함된 속성은 json으로 바꾸지 않도록
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
