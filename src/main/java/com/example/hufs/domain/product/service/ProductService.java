package com.example.hufs.domain.product.service;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.product.dto.ProductListResponseDto;
import com.example.hufs.domain.product.entity.Product;
import com.example.hufs.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductListResponseDto> getAllProduct() {

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new BaseException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        return  products.stream()
                .map(product -> ProductListResponseDto.builder()
                        .productId(product.getId())
                        .productName(product.getProductName())
                        .imageUrl(product.getImageUrl())
                        .quantity(product.getPrice())
                        .build())
                .toList();
    }
}
