package com.learning.iceshop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IceSortRepository extends JpaRepository<IceSort, Long>,
        JpaSpecificationExecutor<IceSort> {
    List<IceSort> findByScoreGreaterThan(int score);
    @Query("SELECT i.iceSort FROM IceSort i WHERE i.score > :score")
    List<String> findIceSortsByScoreGreaterThan(@Param("score") int score);
}