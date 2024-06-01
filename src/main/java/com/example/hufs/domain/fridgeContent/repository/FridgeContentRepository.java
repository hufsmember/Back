package com.example.hufs.domain.fridgeContent.repository;

import com.example.hufs.domain.fridgeContent.entity.FridgeContent;
import com.example.hufs.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FridgeContentRepository extends JpaRepository<FridgeContent, Long> {
    Optional<FridgeContent> findByMember(Member member);

    @Query(nativeQuery = true, value = """
    select f
    from fridge_content f
    where f.food_id = :foodId and f.member_id = :memberId
    """)
    Optional<FridgeContent> findByFoodIdAndMemberId(@Param("foodId") Long foodId, @Param("memberId") Long memberId);
}
