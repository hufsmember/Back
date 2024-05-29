package com.example.hufs.domain.fridgeContent.repository;

import com.example.hufs.domain.fridgeContent.entity.FridgeContent;
import com.example.hufs.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FridgeContentRepository extends JpaRepository<FridgeContent, Long> {

    Optional<FridgeContent> findByMember(Member member);
}
