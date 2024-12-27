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
public class TimeDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    private String actualWork;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimeDo(String actualWork, LocalDateTime startTime, LocalDateTime endTime) {
        this.actualWork = actualWork;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isTimeInRange(LocalDateTime time) {
        return !time.isBefore(startTime) && !time.isAfter(endTime);
    }
}
