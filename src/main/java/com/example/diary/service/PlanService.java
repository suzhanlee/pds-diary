package com.example.diary.service;

import com.example.diary.domain.Day;
import com.example.diary.domain.Member;
import com.example.diary.domain.TimeDo;
import com.example.diary.domain.TimePlan;
import com.example.diary.domain.WeekPlan;
import com.example.diary.dto.CreateDaySeeRq;
import com.example.diary.dto.CreateTimeDoRq;
import com.example.diary.dto.CreateTimePlanRq;
import com.example.diary.dto.CreateWeekPlanRq;
import com.example.diary.dto.UpdateDaySeeRq;
import com.example.diary.dto.UpdateTimeDoRq;
import com.example.diary.dto.UpdateTimePlanRq;
import com.example.diary.dto.UpdateWeekPlanRq;
import com.example.diary.repository.DayRepository;
import com.example.diary.repository.MemberRepository;
import com.example.diary.repository.TimeDoRepository;
import com.example.diary.repository.TimePlanRepository;
import com.example.diary.repository.WeekRepository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService {

    private final MemberRepository memberRepository;
    private final WeekRepository weekRepository;
    private final DayRepository dayRepository;
    private final TimePlanRepository timePlanRepository;
    private final TimeDoRepository timeDoRepository;

    public Long createWeekPlan(CreateWeekPlanRq rq) {
        Member member = memberRepository.findById(rq.getMemberId()).orElseThrow();
        WeekPlan weekPlan = new WeekPlan(member, rq.getDate(), rq.getPlan());
        weekRepository.save(weekPlan);
        return weekPlan.getId();
    }

    public Long createTimePlan(CreateTimePlanRq rq) {
        Member member = memberRepository.findById(rq.getMemberId()).orElseThrow();
        WeekPlan weekPlan = findTotalWeekInfoOrCreateWeek(rq.getStartTime(), rq.getEndTime(), member);

        Day day = findOrCreateDayPlanWithDateTime(weekPlan, rq.getStartTime());

        TimePlan timePlan = new TimePlan(rq.getPlan(), rq.getStartTime(), rq.getEndTime());
        timePlanRepository.save(timePlan);
        day.addTimePlan(timePlan);
        return timePlan.getId();
    }

    public Long createTimeDo(CreateTimeDoRq rq) {
        Member member = memberRepository.findById(rq.getMemberId()).orElseThrow();
        WeekPlan weekPlan = findTotalWeekInfoOrCreateWeek(rq.getStartTime(), rq.getEndTime(), member);

        Day day = findOrCreateDayPlanWithDateTime(weekPlan, rq.getStartTime());

        TimeDo timeDo = new TimeDo(rq.getActualWork(), rq.getStartTime(), rq.getEndTime());
        timeDoRepository.save(timeDo);
        day.addTimeDo(timeDo);
        return timeDo.getId();
    }

    private WeekPlan findTotalWeekInfoOrCreateWeek(LocalDateTime startTime, LocalDateTime endTime, Member member) {
        return weekRepository.findTotalWeekInfoWithHourPlan(
                startTime.toLocalDate(),
                endTime.toLocalDate(),
                member.getId()
        ).orElseGet(() -> {
            WeekPlan weekPlan = new WeekPlan(member, startTime.toLocalDate());
            weekRepository.save(weekPlan);
            return weekPlan;
        });
    }

    private Day findOrCreateDayPlanWithDateTime(WeekPlan weekPlan, LocalDateTime dateTime) {
        if (weekPlan.isPersistedWeek()) {
            Day day = weekPlan.ensureDayPlanExists(dateTime);
            dayRepository.save(day);
            return day;
        }
        Day day = new Day(dateTime.toLocalDate());
        dayRepository.save(day);
        day.addWeek(weekPlan);
        return day;
    }

    public Long createDaySee(CreateDaySeeRq rq) {
        Member member = memberRepository.findById(rq.getMemberId()).orElseThrow();
        WeekPlan weekPlan = findWeekInfoOrCreateWeek(rq.getDate(), member);
        return findOrCreateDay(rq.getDate(), rq.getSee(), weekPlan);
    }

    private Long findOrCreateDay(LocalDate date, String see, WeekPlan weekPlan) {
        return dayRepository.findByDayAndWeekPlanId(date, weekPlan.getId())
                .map(day -> {
                    day.updateSee(see);
                    return day.getId();
                })
                .orElseGet(() -> {
                    Day day = new Day(date, see);
                    weekPlan.addDay(day);
                    dayRepository.save(day);
                    return day.getId();
                });
    }

    private WeekPlan findWeekInfoOrCreateWeek(LocalDate date, Member member) {
        return weekRepository.findWeekWithHourPlan(
                date,
                member.getId()
        ).orElseGet(() -> {
            WeekPlan weekPlan = new WeekPlan(member, date);
            weekRepository.save(weekPlan);
            return weekPlan;
        });
    }

    public void updateWeekPlan(Long weekId, UpdateWeekPlanRq rq) {
        WeekPlan weekPlan = weekRepository.findById(weekId).orElseThrow();
        weekPlan.updatePlan(rq.getPlan());
    }

    public void updateTimePlan(Long timePlanId, UpdateTimePlanRq rq) {
        TimePlan timePlan = timePlanRepository.findById(timePlanId).orElseThrow();
        timePlan.updatePlan(rq.getPlan());
    }

    public void updateTimeDo(Long timeDoId, UpdateTimeDoRq rq) {
        TimeDo timeDo = timeDoRepository.findById(timeDoId).orElseThrow();
        timeDo.updateDo(rq.getActualWork());
    }

    public void updateDaySee(Long daySeeId, UpdateDaySeeRq rq) {
        Day day = dayRepository.findById(daySeeId).orElseThrow();
        day.updateSee(rq.getSee());
    }
}
