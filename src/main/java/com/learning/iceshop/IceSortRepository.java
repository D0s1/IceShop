package com.learning.iceshop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IceSortRepository extends JpaRepository<IceSort, Long> {
    List<IceSort> findByScoreGreaterThan(int score);
}