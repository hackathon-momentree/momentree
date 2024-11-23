package com.dteam.momentree.domain.diary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByCreateUserAndDay(Long createUser, int day);

    boolean existsByCreateUserAndDay(Long createUser, int day);

    boolean existsByCreateUserAndLocation(Long createUser, Long location);

    Optional<Diary> findByCreateUserAndLocation(Long createUser, Long location);

    @Query("SELECT d FROM Diary d WHERE d.location IS NULL AND d.createUser = :createUser")
    List<Diary> findDiariesWithoutLocationByUser(Long createUser);
}
