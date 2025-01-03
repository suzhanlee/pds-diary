package com.example.diary.domain;

import jakarta.persistence.CascadeType;
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
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate day;
    private String see;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_plan_id")
    private WeekPlan weekPlan;

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimePlan> timePlans = new HashSet<>();

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TimeDo> timeDos = new HashSet<>();

    public Day(LocalDate day, String see) {
        this.day = day;
        this.see = see;
    }

    public Day(LocalDate day) {
        this.day = day;
    }

    public boolean hasDayPlan(LocalDateTime time) {
        return this.day.equals(time.toLocalDate());
    }

    public boolean hasMatchedTimePlan(LocalDateTime time) {
        return this.timePlans.stream().anyMatch(tp -> tp.isTimeInRange(time));
    }

    public boolean hasMatchedTimeDo(LocalDateTime time) {
        return this.timeDos.stream().anyMatch(td -> td.isTimeInRange(time));
    }

    public void addWeek(WeekPlan weekPlan) {
        this.weekPlan = weekPlan;
    }

    public void addTimePlan(TimePlan timePlan) {
        this.timePlans.add(timePlan);
        timePlan.addDay(this);
    }

    public void addTimeDo(TimeDo timeDo) {
        this.timeDos.add(timeDo);
        timeDo.addDay(this);
    }

    public void updateSee(String see) {
        this.see = see;
    }
}
