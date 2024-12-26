package com.example.diary.service;

import com.example.diary.domain.Week;
import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WeekService {

    private final WeekRepository weekRepository;

    public Long createWeekPlan(CreateWeekPlanRq rq) {
        Week week = new Week(rq.getDate(), rq.getPlan());
        weekRepository.save(week);
        return week.getId();
    }
}
