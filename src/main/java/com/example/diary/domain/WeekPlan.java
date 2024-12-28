package com.example.diary.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WeekPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String plan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "weekPlan")
    private List<Day> days = new ArrayList<>();

    public WeekPlan(LocalDate date, String plan) {
        this.date = date;
        this.plan = plan;
    }

    public WeekPlan(LocalDate date) {
        this.date = date;
    }

    public WeekPlan(Member member, LocalDate date, String plan) {
        this(date, plan);
        addMember(member);
    }

    public WeekPlan(Member member, LocalDate date) {
        this(date);
        addMember(member);
    }

    public static WeekPlan empty() {
        return new WeekPlan();
    }

    public void addDay(Day day) {
        this.days.add(day);
        day.addWeek(this);
    }

    public void addMember(Member member) {
        this.member = member;
        member.addWeekPlan(this);
    }

    public Day ensureDayPlanExists(LocalDateTime time) {
        return days.stream()
                .filter(day -> day.hasDayPlan(time))
                .findFirst()
                .orElseGet(() -> {
                    Day day = new Day(time.toLocalDate());
                    addDay(day);
                    return day;
                });
    }

    public boolean isPersistedWeek() {
        return id != null;
    }

    public void updatePlan(String plan) {
        this.plan = plan;
    }
}
