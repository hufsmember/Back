package com.example.hufs.common.config;

import com.example.hufs.common.security.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final @Lazy JwtAuthenticationFilter jwtAuthenticationFilter;

    //BCrypt 방식으로 password 인코딩 진행
    @Bean
    public PasswordEncoder encoder(){ return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/public-api/**", "/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/members/check/register").permitAll()
                                .requestMatchers(HttpMethod.POST, "/members/login").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).build();
    }

    @Bean
    @Profile(value = "local")
    public WebSecurityCustomizer configureH2ConSoleEnable(){
        return web -> web
                .ignoring()
                .requestMatchers(PathRequest.toH2Console());
    }

    @Bean
    @Profile(value = "develop")
    public WebSecurityCustomizer configureSwaggerEnable() {
        return web -> web
                .ignoring()
                .requestMatchers("/swagger");
    }
}
