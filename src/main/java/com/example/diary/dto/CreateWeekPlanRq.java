package com.example.diary.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateWeekPlanRq {

    private long memberId;
    private LocalDate date;
    private String plan;
}
