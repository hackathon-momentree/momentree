package com.dteam.momentree.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByDay(int day);

    boolean existsByDay(int day);
}
