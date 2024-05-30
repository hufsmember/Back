package com.example.hufs.common.controller;

import com.example.hufs.common.response.BaseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
public class HealthCheckController {

    @GetMapping("/server")
    public BaseResponseDTO<Void> serverHealthCheck(){
        return BaseResponseDTO.ok();
    }
}
