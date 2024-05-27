package com.example.hufs.domain.member.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.allergy.entity.Allergy;
import com.example.hufs.domain.cuisine.entity.Cuisine;
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

    @ManyToMany
    @JoinTable(
            name = "member_cuisine",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "cuisine_id")
    )
    private Set<Cuisine> preferredCuisines;

    @Column
    private String nonPreferredCuisine;

    @Column
    private String preferredIngredient;

    @Column
    private String nonPreferredIngredient;

    @Column
    private Boolean termAgreed;

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

    public void setGender(Gender gender) {
        this.genderType = gender;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroupType = ageGroup;
    }

    public void setVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }
}
