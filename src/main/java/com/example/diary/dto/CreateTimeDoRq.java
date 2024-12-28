package com.example.diary.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CreateTimeDoRq {

    private long memberId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String actualWork;
}
