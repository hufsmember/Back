package com.example.hufs.domain.product.controller;

import com.example.hufs.common.response.BaseResponseDTO;
import com.example.hufs.domain.product.dto.ProductListResponseDto;
import com.example.hufs.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/detail")
    public BaseResponseDTO<List<ProductListResponseDto>> getAllProduct(
    ) {
        return BaseResponseDTO.okWithData(productService.getAllProduct());
    }
}
