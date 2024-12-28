package com.example.diary.repository;

import com.example.diary.domain.Day;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {

    Optional<Day> findByDayAndWeekPlanId(LocalDate date, Long weekPlanId);
}
