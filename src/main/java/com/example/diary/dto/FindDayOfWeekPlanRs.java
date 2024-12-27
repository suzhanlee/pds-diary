package com.example.diary.dto;

import com.example.diary.domain.Week;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindDayOfWeekPlanRs {

    private LocalDate date;
    private String plan;
    private DaySeeDto daySeeDto;

    public static FindDayOfWeekPlanRs createByWeek(Week week) {
        FindDayOfWeekPlanRs rs = new FindDayOfWeekPlanRs();
        rs.date = week.getDate();
        rs.plan = week.getPlan();
        rs.daySeeDto = DaySeeDto.create(week.getDays().getFirst());
        return rs;
    }

    public static FindDayOfWeekPlanRs empty(LocalDate date) {
        FindDayOfWeekPlanRs rs = new FindDayOfWeekPlanRs();
        rs.date = date;
        rs.plan = "주간 계획을 작성해주세요!";
        rs.daySeeDto = DaySeeDto.empty();
        return rs;
    }
}
