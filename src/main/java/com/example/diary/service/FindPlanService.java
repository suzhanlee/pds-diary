package com.example.diary.service;

import com.example.diary.dto.FindDayOfWeekPlanRs;
import com.example.diary.repository.WeekRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindPlanService {

    private final WeekRepository weekRepository;

    public FindDayOfWeekPlanRs findSpecificDayOfWeekPlan(LocalDate date) {
        return weekRepository.findWeekWithHourPlan(date)
                .map(FindDayOfWeekPlanRs::createByWeek)
                .orElse(FindDayOfWeekPlanRs.empty(date));
    }
}
