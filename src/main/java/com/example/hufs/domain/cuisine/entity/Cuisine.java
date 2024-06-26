package com.example.hufs.domain.cuisine.entity;

import com.example.hufs.common.entity.SoftDeleteBaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cuisine extends SoftDeleteBaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuisine_id")
    private Long id;

    @Column
    private String cuisineType;
}
