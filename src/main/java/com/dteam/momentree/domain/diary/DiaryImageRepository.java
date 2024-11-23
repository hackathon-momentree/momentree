    package com.dteam.momentree.domain.diary;

    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface DiaryImageRepository extends JpaRepository<DiaryImage, Long> {
        List<DiaryImage> findByDiary(Diary diary);
    }
