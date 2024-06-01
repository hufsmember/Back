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
    @Column(name = "member_id")
    private Long id;

    @Column
    private String memberName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Boolean isFamilyExist;

    @Column
    private String genderType;

    @Column
    private String ageGroup;

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
    private String preferredCuisine;

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

    public void setGender(String gender) {
        this.genderType = gender;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public void setVegan(Boolean isVegan) {
        this.isVegan = isVegan;
    }

    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }
}
