package com.example.diary.repository;

import com.example.diary.domain.WeekPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekPlanRepository extends JpaRepository<WeekPlan, Long> {
}
