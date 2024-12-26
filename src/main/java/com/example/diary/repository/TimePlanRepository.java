package com.example.diary.repository;

import com.example.diary.domain.TimePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimePlanRepository extends JpaRepository<TimePlan, Long> {
}
