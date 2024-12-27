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
import java.util.ArrayList;
import java.util.List;
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
    @JoinColumn(name = "week_id")
    private Week week;

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimePlan> timePlans = new ArrayList<>();

    @OneToMany(mappedBy = "week", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeDo> timeDos = new ArrayList<>();

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

    public void addWeek(Week week) {
        this.week = week;
    }

    public void addTimePlan(TimePlan timePlan) {
        this.timePlans.add(timePlan);
    }

    public void addTimeDo(TimeDo timeDo) {
        this.timeDos.add(timeDo);
    }
}
