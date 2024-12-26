package com.example.diary.repository;

import com.example.diary.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeekRepository extends JpaRepository<Week, Long> {
}
