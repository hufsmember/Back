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
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;
    private final WebClient webClient;

    @Transactional(readOnly = true)
    public List<ProductListResponseDto> getAllProduct() {

        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new BaseException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        return  products.stream()
                .map(product -> ProductListResponseDto.builder()
                        .productId(product.getId())
                        .imageUrl(product.getImageUrl())
                        .quantity(product.getPrice())
                        .build())
                .toList();
    }

    public ProductListResponseDto getMyProduct(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()-> new BaseException(ErrorCode.MEMBER_NOT_EXIST));

        //AI로 api 요청하기
        String url = "배포 url/api/shinsegae/products";

        //요청받는 데이터 클래스를 따로 만들어줘야함
        Mono<ProductListResponseDto> responseMono = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(url)
                        .queryParam("category","food")
                        .build())
                .retrieve()
                .bodyToMono(ProductListResponseDto.class);
        //요청으로 받은 데이터(Json)를 내가 보기 좋게 변환하여 담는다.
        ProductListResponseDto productListResponseDto = responseMono.block();

//        if (productListResponseDto != null) {
//            List<Product> product = productListResponseDto.어쩌고.stream().map(dto -> {
//
//                }).collect(Collectors.toList()));
//                // 필요한 다른 필드들 설정
//
//            }).collect(Collectors.toList());

//        리스트 dto로 넣을 거 넣고, 전체 결과는 Recipe 테이블에 save
//            productRepository.saveAll(recipes);

        return null;
    }
}
