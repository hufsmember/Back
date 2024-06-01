package com.example.hufs.domain.product.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.product.entity.enumtype.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private Long id;

    @Column
    private String productName;

    @Column
    private Integer price;

    @Column
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @Column
    private LocalDateTime deletedAt;
}

