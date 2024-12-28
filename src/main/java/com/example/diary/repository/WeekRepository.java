package com.example.diary.repository;

import com.example.diary.domain.WeekPlan;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WeekRepository extends JpaRepository<WeekPlan, Long> {

    @Query("SELECT DISTINCT w FROM WeekPlan w " +
            "JOIN FETCH w.member m " +
            "LEFT JOIN FETCH w.days d " +
            "LEFT JOIN FETCH d.timePlans " +
            "LEFT JOIN FETCH d.timeDos " +
            "WHERE w.date BETWEEN :weekStart AND :weekEnd " +
            "AND m.id = :memberId")
    Optional<WeekPlan> findTotalWeekInfoWithHourPlan(
            @Param("weekStart") LocalDate weekStart,
            @Param("weekEnd") LocalDate weekEnd,
            @Param("memberId") long memberId
    );

    @Query("SELECT DISTINCT w FROM WeekPlan w " +
            "JOIN FETCH w.member m " +
            "LEFT JOIN FETCH w.days d " +
            "LEFT JOIN FETCH d.timePlans tp " +
            "LEFT JOIN FETCH d.timeDos td " +
            "WHERE FUNCTION('week', w.date) = FUNCTION('week', :date) " +
            "AND FUNCTION('YEAR', w.date) = FUNCTION('YEAR', :date) " +
            "AND m.id = :memberId")
    Optional<WeekPlan> findWeekWithHourPlan(@Param("date") LocalDate date, @Param("memberId") long memberId);

    Optional<WeekPlan> findByDateAndMemberId(LocalDate date, Long memberId);
}
