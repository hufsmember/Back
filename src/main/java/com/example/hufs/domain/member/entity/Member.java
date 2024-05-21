package com.example.hufs.domain.member.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.allergy.entity.Allergy;
import com.example.hufs.domain.member.entity.enumtype.AgeGroup;
import com.example.hufs.domain.member.entity.enumtype.Gender;
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
public class Member extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean isFamilyExist;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender genderType;

    @Column
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroupType;

    @Column
    private Boolean isVegan;

    @Column
    private String preferredCuisine;

    @Column
    private String nonPreferredCuisine;

    @Column
    private String preferredIngredient;

    @Column
    private String nonPreferredIngredient;

    @ManyToMany
    @Column
    @JoinTable(
            name = "member_allergy",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private Set<Allergy> allergies;

    @Column
    private LocalDateTime deletedAt;
}
