package com.example.hufs.domain.food.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import com.example.hufs.domain.allergy.entity.Allergy;
import com.example.hufs.domain.food.entity.enumtype.StorageMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Food extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String foodName;

    @Column
    @Enumerated(EnumType.STRING)
    private StorageMethod storageMethod;

    @Column
    private Boolean isVegan;

    @ManyToMany
    @JoinTable(
            name = "food_allergy",
            joinColumns = @JoinColumn(name = "food_id"),
            inverseJoinColumns = @JoinColumn(name = "allergy_id")
    )
    private Set<Allergy> allergies;

    @Column
    private LocalDateTime deletedAt;
}
