package com.example.hufs.domain.shoppingList.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.food.entity.Food;
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
public class ShoppingList extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    @Column
    private Integer quantity;

    @Column
    private LocalDateTime deletedAt;
}
