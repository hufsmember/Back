package com.example.hufs.domain.product.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.member.entity.Member;
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
    private String category;

    @Column
    private String description;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private LocalDateTime deletedAt;
}

