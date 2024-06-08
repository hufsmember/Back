package com.example.hufs.domain.product.repository;

import com.example.hufs.domain.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByMember_Id(Long memberId);
}
