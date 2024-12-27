package com.example.diary.repository;

import com.example.diary.domain.Week;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeekRepository extends JpaRepository<Week, Long> {

    @Query("SELECT DISTINCT w FROM Week w " +
            "LEFT JOIN FETCH w.days d " +
            "LEFT JOIN FETCH d.timePlans tp " +
            "LEFT JOIN FETCH d.timeDos td " +
            "WHERE FUNCTION('week', w.date) = FUNCTION('week', :date) " +
            "AND FUNCTION('YEAR', w.date) = FUNCTION('YEAR', :date) " +
            "AND (tp IS NULL OR (tp.startTime <= :endTime AND tp.endTime >= :startTime)) " +
            "AND (td IS NULL OR (td.startTime <= :endTime AND td.endTime >= :startTime))")
    Optional<Week> findTotalWeekInfoWithHourPlan(
            @Param("weekDate") LocalDate weekDate,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );

    @Query("SELECT DISTINCT w FROM Week w " +
            "LEFT JOIN FETCH w.days d " +
            "LEFT JOIN FETCH d.timePlans tp " +
            "LEFT JOIN FETCH d.timeDos td " +
            "WHERE FUNCTION('week', w.date) = FUNCTION('week', :date) " +
            "AND FUNCTION('YEAR', w.date) = FUNCTION('YEAR', :date) " +
            "AND d.day = :date")
    Optional<Week> findWeekWithHourPlan(@Param("date") LocalDate date);
}
