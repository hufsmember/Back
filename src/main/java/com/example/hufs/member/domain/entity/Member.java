package com.example.hufs.member.domain.entity;

import com.example.hufs.member.domain.entity.enumtype.AgeGroup;
import com.example.hufs.member.domain.entity.enumtype.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    private String password;
    private Boolean isFamilyExist;
    @Enumerated(EnumType.STRING)
    private Gender genderType;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroupType;
    private Boolean isVegan;
    private String preferredCuisine;
    private String nonPreferredCuisine;
    private String preferredIngredient;
    private String nonPreferredIngredient;

}
