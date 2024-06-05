package com.example.hufs.domain.basket.service;

import com.example.hufs.common.exception.BaseException;
import com.example.hufs.common.exception.ErrorCode;
import com.example.hufs.domain.basket.dto.BasketRequestDto;
import com.example.hufs.domain.basket.entity.Basket;
import com.example.hufs.domain.basket.repository.BasketRepository;
import com.example.hufs.domain.member.entity.Member;
import com.example.hufs.domain.member.repository.MemberRepository;
import com.example.hufs.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BasketService {

    private final BasketRepository basketRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    public void saveBasket(List<BasketRequestDto> requestDtos, String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        List<Basket> baskets = requestDtos.stream()
                .map(requestDto -> Basket.builder()
                        .price(requestDto.price())
                        .quantity(requestDto.quantity())
                        .member(member)
                        .product(productRepository.findById(requestDto.productId())
                                .orElseThrow(()->new BaseException(ErrorCode.PRODUCT_NOT_FOUND)))
                        .build())
                .toList();

        basketRepository.saveAll(baskets);
    }
}
