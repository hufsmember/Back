package com.example.hufs.domain.allergy.repository;

import com.example.hufs.domain.allergy.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    List<Allergy> findByAllergyNameIn(Set<String> allergyNames);
}
