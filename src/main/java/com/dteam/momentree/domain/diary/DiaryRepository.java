package com.dteam.momentree.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByDay(int day);

    boolean existsByDay(int day);

    boolean existsByLocation(Long location);

    Optional<Diary> findByLocation(Long location);

    @Query("SELECT d FROM Diary d WHERE d.location IS NULL")
    List<Diary> findDiariesWithoutLocation();
}
