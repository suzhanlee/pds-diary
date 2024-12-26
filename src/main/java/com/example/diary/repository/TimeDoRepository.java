package com.example.diary.repository;

import com.example.diary.domain.TimeDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeDoRepository extends JpaRepository<TimeDo, Long> {
}
