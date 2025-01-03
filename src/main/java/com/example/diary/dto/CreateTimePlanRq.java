package com.example.diary.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTimePlanRq {

    private long memberId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String plan;
}
