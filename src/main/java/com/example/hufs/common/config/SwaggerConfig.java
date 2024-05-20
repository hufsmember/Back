package com.example.hufs.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "그로시 api 명세서",
                description = "강화학습을 활용한 장보기 시스템 구현 프로젝트"
        )

)
public class SwaggerConfig {
    String root = "com.example.hufs";
    String[] paths = {
            root+".member.controller"
    };

    @Bean
    public GroupedOpenApi getGroupedApi() {
        return GroupedOpenApi.builder()
                .group("Group")
                .packagesToScan(paths)
                .build();
    }

}
