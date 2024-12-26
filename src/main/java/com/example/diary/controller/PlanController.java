package com.example.diary.controller;

import com.example.diary.dto.CreateTimeDoRq;
import com.example.diary.dto.CreateTimePlanRq;
import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;

    @PostMapping("/plan/week")
    public Long createWeekPlan(@RequestBody CreateWeekPlanRq rq) {
        return planService.createWeekPlan(rq);
    }

    @PostMapping("/plan/time")
    public Long createTimePlan(@RequestBody CreateTimePlanRq rq) {
        return planService.createTimePlan(rq);
    }

    @PostMapping("/plan/time/do")
    public Long createTimeDo(@RequestBody CreateTimeDoRq rq) {
        return planService.createTimeDo(rq);
    }
}
