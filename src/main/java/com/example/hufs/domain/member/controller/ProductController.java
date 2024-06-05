package com.example.hufs.domain.member.controller;

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
<<<<<<< HEAD:src/main/java/com/example/hufs/domain/member/controller/ProductController.java
=======
            @AuthenticationPrincipal MemberDetail memberDetail
>>>>>>> feature/36:src/main/java/com/example/hufs/domain/product/controller/ProductController.java
    ) {
        return BaseResponseDTO.okWithData(productService.getAllProduct());
    }

    @GetMapping("/my-product")
    public BaseResponseDTO<ProductListResponseDto> getMyProduct(
            @AuthenticationPrincipal MemberDetail memberDetail
    ) {
        return BaseResponseDTO.okWithData(productService.getMyProduct(memberDetail.getUsername()));
    }
}
