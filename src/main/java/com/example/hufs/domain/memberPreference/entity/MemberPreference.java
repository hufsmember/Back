package com.example.hufs.domain.memberPreference.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.member.entity.Member;
import com.example.hufs.domain.recipe.entity.Recipe;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class MemberPreference extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @Min(0)
    @Max(5)
    @Column
    private Integer preferenceLevel;

    @Column
    private Boolean liked;

    @Column
    private LocalDateTime deletedAt;
}
