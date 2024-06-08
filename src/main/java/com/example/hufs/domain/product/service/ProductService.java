package com.example.hufs.domain.product.service;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.member.entity.Member;
import com.example.hufs.domain.member.repository.MemberRepository;
import com.example.hufs.domain.product.dto.ProductListResponseDto;
import com.example.hufs.domain.product.entity.Product;
import com.example.hufs.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    public List<ProductListResponseDto> getAllProduct() {

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new BaseException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        return  products.stream()
                .map(product -> ProductListResponseDto.builder()
                        .productId(product.getId())
                        .imageUrl(product.getImageUrl())
                        .price(product.getPrice())
                        .build())
                .toList();
    }

    public List<ProductListResponseDto> getMyProduct(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        List<Product> products = productRepository.findByMember_Id(member.getId());

        return products.stream()
                .map(product -> ProductListResponseDto.builder()
                        .productId(product.getId())
                        .imageUrl(product.getImageUrl())
                        .price(product.getPrice())
                        .build())
                .toList();
    }
}
