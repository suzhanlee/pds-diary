package com.example.diary.dto;

import com.example.diary.domain.Day;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DaySeeDto {

    private String see;
    private List<TimePlanDto> timePlanDtos;
    private List<TimeDoDto> timeDoDtos;

    public static DaySeeDto create(Day day) {
        DaySeeDto daySeeDto = new DaySeeDto();
        daySeeDto.see = day.getSee();
        daySeeDto.timePlanDtos = day.getTimePlans().stream()
                .map(tp -> new TimePlanDto(tp.getStartTime(), tp.getEndTime(), tp.getPlan()))
                .toList();
        daySeeDto.timeDoDtos = day.getTimeDos().stream()
                .map(tp -> new TimeDoDto(tp.getStartTime(), tp.getEndTime(), tp.getActualWork()))
                .toList();
        return daySeeDto;
    }

    public static DaySeeDto empty() {
        DaySeeDto daySeeDto = new DaySeeDto();
        daySeeDto.see = "오늘 하루를 기록해보세요!";
        daySeeDto.timePlanDtos = List.of();
        daySeeDto.timeDoDtos = List.of();
        return daySeeDto;
    }
}
