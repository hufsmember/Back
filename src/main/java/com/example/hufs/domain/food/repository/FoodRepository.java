package com.example.hufs.domain.food.repository;

import com.example.hufs.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {

    Optional<Food> findByIdAndStorageMethod(Long id, String storageMethod);
}
