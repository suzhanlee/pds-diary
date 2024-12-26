package com.example.diary.service;

import com.example.diary.domain.DaySee;
import com.example.diary.domain.TimeDo;
import com.example.diary.domain.TimePlan;
import com.example.diary.domain.WeekPlan;
import com.example.diary.dto.CreateDaySeeRq;
import com.example.diary.dto.CreateTimeDoRq;
import com.example.diary.dto.CreateTimePlanRq;
import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.repository.DaySeeRepository;
import com.example.diary.repository.TimeDoRepository;
import com.example.diary.repository.TimePlanRepository;
import com.example.diary.repository.WeekPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService {

    private final WeekPlanRepository weekPlanRepository;
    private final TimePlanRepository timePlanRepository;
    private final TimeDoRepository timeDoRepository;
    private final DaySeeRepository daySeeRepository;

    public Long createWeekPlan(CreateWeekPlanRq rq) {
        WeekPlan weekPlan = new WeekPlan(rq.getDate(), rq.getPlan());
        weekPlanRepository.save(weekPlan);
        return weekPlan.getId();
    }

    public Long createTimePlan(CreateTimePlanRq rq) {
        TimePlan timePlan = new TimePlan(rq.getTime(), rq.getPlan());
        timePlanRepository.save(timePlan);
        return timePlan.getId();
    }

    public Long createTimeDo(CreateTimeDoRq rq) {
        TimeDo timeDo = new TimeDo(rq.getTime(), rq.getActualWork());
        timeDoRepository.save(timeDo);
        return timeDo.getId();
    }

    public Long createDaySee(CreateDaySeeRq rq) {
        DaySee daySee = new DaySee(rq.getDate(), rq.getSee());
        daySeeRepository.save(daySee);
        return daySee.getId();
    }
}
