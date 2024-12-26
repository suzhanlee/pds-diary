package com.example.diary.service;

import com.example.diary.domain.Time;
import com.example.diary.domain.Week;
import com.example.diary.dto.CreateTimePlanRq;
import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.repository.TimeRepository;
import com.example.diary.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService {

    private final WeekRepository weekRepository;
    private final TimeRepository timeRepository;

    public Long createWeekPlan(CreateWeekPlanRq rq) {
        Week week = new Week(rq.getDate(), rq.getPlan());
        weekRepository.save(week);
        return week.getId();
    }

    public Long createTimePlan(CreateTimePlanRq rq) {
        Time time = new Time(rq.getCurrentTime(), rq.getPlan());
        timeRepository.save(time);
        return time.getId();
    }
}
