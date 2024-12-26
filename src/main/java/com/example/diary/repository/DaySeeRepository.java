package com.example.diary.repository;

import com.example.diary.domain.DaySee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaySeeRepository extends JpaRepository<DaySee, Long> {
}
