package com.example.hufs.domain.fridgeContent.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.food.entity.Food;
import com.example.hufs.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FridgeContent extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "fridge_content_food",
            joinColumns = @JoinColumn(name = "fridge_content_id"),
            inverseJoinColumns = @JoinColumn(name = "food_id")
    )
    private Set<Food> foods;

    @Column
    private Integer quantity;

    @Column
    private LocalDateTime purchaseDate;

    @Column
    private LocalDateTime expiryDate;

    @Column
    private Integer refridgeTemp;

    @Column
    private Integer freezeTemp;

    @Column
    private LocalDateTime deletedAt;
}
