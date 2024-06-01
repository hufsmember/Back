package com.example.hufs.domain.fridgeContent.repository;

import com.example.hufs.domain.fridgeContent.entity.FridgeContent;
import com.example.hufs.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FridgeContentRepository extends JpaRepository<FridgeContent, Long> {
    Optional<FridgeContent> findByMember(Member member);

    @Query("""
    select distinct f
    from FridgeContent f
    where f.member.id=:memberId
""")
    Optional<FridgeContent> findByMemberId(@Param("memberId") Long memberId);

    @Query(nativeQuery = true, value = """
    select f.*
    from fridge_content f
    where f.food_id = :foodId and f.member_id = :memberId
    """)
    Optional<FridgeContent> findByFoodIdAndMemberId(@Param("foodId") Long foodId, @Param("memberId") Long memberId);

    @Query(nativeQuery = true, value = """
    select f.food_id
    from fridge_content f
    where f.fridge_id=:id
    """)
    List<Long> findFoodIdById(@Param("id") Long id);
}
