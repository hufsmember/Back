package com.example.hufs.domain.recipe.entity;

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
public class Recipe extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recipe_id")
    private Long id;

    @Column
    private String recipeName;

    @Column
    private String cuisineType;

    @Column
    private String description;

    @Column
    private String recipeImageUrl;

    @Column
    private Boolean isVegan;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Column
    private LocalDateTime deletedAt;
}
