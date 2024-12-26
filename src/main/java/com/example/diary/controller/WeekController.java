package com.example.diary.controller;

import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.service.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeekController {

    private final WeekService weekService;

    @PostMapping("/week")
    public Long createWeekPlan(@RequestBody CreateWeekPlanRq rq) {
        return weekService.createWeekPlan(rq);
    }
}
