package com.example.diary.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String plan;

    @OneToMany(mappedBy = "week")
    private List<Day> days = new ArrayList<>();

    public Week(LocalDate date, String plan) {
        this.date = date;
        this.plan = plan;
    }

    public Week(LocalDate date) {
        this.date = date;
    }

    public static Week empty() {
        return new Week();
    }

    public void addDay(Day day) {
        this.days.add(day);
        day.addWeek(this);
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
}
