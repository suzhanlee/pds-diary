package com.example.diary.controller;

import com.example.diary.dto.CreateDaySeeRq;
import com.example.diary.dto.CreateTimeDoRq;
import com.example.diary.dto.CreateTimePlanRq;
import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.dto.FindDayOfWeekPlanRq;
import com.example.diary.dto.FindDayOfWeekPlanRs;
import com.example.diary.dto.UpdateDaySeeRq;
import com.example.diary.dto.UpdateTimeDoRq;
import com.example.diary.dto.UpdateTimePlanRq;
import com.example.diary.dto.UpdateWeekPlanRq;
import com.example.diary.service.FindPlanService;
import com.example.diary.service.PlanService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final FindPlanService findPlanService;

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

    @PostMapping("/plan/day/see")
    public Long createDaySee(@RequestBody CreateDaySeeRq rq) {
        return planService.createDaySee(rq);
    }

    @GetMapping("/plan/week/{date}")
    public FindDayOfWeekPlanRs findDayOfWeekPlan(@PathVariable("date")LocalDate date) {
        return findPlanService.findSpecificDayOfWeekPlan(date);
    }

    @PutMapping("/plan/week/{weekId}")
    public void updateWeekPlan(@PathVariable Long weekId, @RequestBody UpdateWeekPlanRq rq) {
        planService.updateWeekPlan(weekId, rq);
    }

    @PutMapping("/plan/time/{timePlanId}")
    public void updateTimePlan(@PathVariable Long timePlanId, @RequestBody UpdateTimePlanRq rq) {
        planService.updateTimePlan(timePlanId, rq);
    }

    @PutMapping("/plan/time/do/{timeDoId}")
    public void updateTimeDo(@PathVariable Long timeDoId, @RequestBody UpdateTimeDoRq rq) {
        planService.updateTimeDo(timeDoId, rq);
    }

    @PutMapping("/plan/day/see/{daySeeId}")
    public void updateDaySee(@PathVariable Long daySeeId, @RequestBody UpdateDaySeeRq rq) {
        planService.updateDaySee(daySeeId, rq);
    }
}
