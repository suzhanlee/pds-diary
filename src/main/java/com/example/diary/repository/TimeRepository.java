package com.example.diary.repository;

import com.example.diary.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository  extends JpaRepository<Time, Long> {
}
