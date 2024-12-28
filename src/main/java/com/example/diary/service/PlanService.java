package com.example.diary.service;

import com.example.diary.domain.Day;
import com.example.diary.domain.TimeDo;
import com.example.diary.domain.TimePlan;
import com.example.diary.domain.Week;
import com.example.diary.dto.CreateDaySeeRq;
import com.example.diary.dto.CreateTimeDoRq;
import com.example.diary.dto.CreateTimePlanRq;
import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.dto.UpdateWeekPlanRq;
import com.example.diary.repository.DayRepository;
import com.example.diary.repository.WeekRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService {

    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;

    public Long createWeekPlan(CreateWeekPlanRq rq) {
        Week week = new Week(rq.getDate(), rq.getPlan());
        weekRepository.save(week);
        return week.getId();
    }

    public Long createTimePlan(CreateTimePlanRq rq) {
        Week week = findOrCreateWeek(rq.getStartTime(), rq.getEndTime());

        Day day = findOrCreateDayPlanWithDateTime(week, rq.getStartTime());

        TimePlan timePlan = new TimePlan(rq.getPlan(), rq.getStartTime(), rq.getEndTime());
        day.addTimePlan(timePlan);
        return timePlan.getId();
    }

    public Long createTimeDo(CreateTimeDoRq rq) {
        Week week = findOrCreateWeek(rq.getStartTime(), rq.getEndTime());

        Day day = findOrCreateDayPlanWithDateTime(week, rq.getStartTime());

        TimeDo timeDo = new TimeDo(rq.getActualWork(), rq.getStartTime(), rq.getEndTime());
        day.addTimeDo(timeDo);
        return timeDo.getId();
    }

    private Day findOrCreateDayPlanWithDateTime(Week week, LocalDateTime dateTime) {
        if (week.isPersistedWeek()) {
            return week.ensureDayPlanExists(dateTime);
        }
        Day day = new Day(dateTime.toLocalDate());
        day.addWeek(week);
        return day;
    }

    private Week findOrCreateWeek(LocalDateTime startTime, LocalDateTime endTime) {
        return weekRepository.findTotalWeekInfoWithHourPlan(
                startTime.toLocalDate(),
                startTime,
                endTime
        ).orElseGet(() -> new Week(startTime.toLocalDate()));
    }

    public Long createDaySee(CreateDaySeeRq rq) {
        Day day = new Day(rq.getDate(), rq.getSee());
        dayRepository.save(day);
        return day.getId();
    }

    public void updateWeekPlan(Long weekId, UpdateWeekPlanRq rq) {
        Week week = weekRepository.findById(weekId).orElseThrow();
        week.update(rq.getPlan());
    }
}
