package com.example.diary.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class CreateDaySeeRq {

    private long memberId;
    private LocalDate date;
    private String see;
}
