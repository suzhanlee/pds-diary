package com.example.diary.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TimePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plan;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimePlan(String plan, LocalDateTime startTime, LocalDateTime endTime) {
        this.plan = plan;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isTimeInRange(LocalDateTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }

    public void updatePlan(String plan) {
        this.plan = plan;
    }
}
