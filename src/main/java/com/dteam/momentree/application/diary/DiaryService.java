package com.dteam.momentree.application.diary;

import com.dteam.momentree.api.diary.dto.DiaryRequest;
import com.dteam.momentree.api.diary.dto.DiaryResponse;
import com.dteam.momentree.api.diary.dto.ReadDiaryResponse;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.domain.diary.Diary;
import com.dteam.momentree.domain.diary.DiaryImage;
import com.dteam.momentree.domain.diary.DiaryImageRepository;
import com.dteam.momentree.domain.diary.DiaryRepository;
import com.dteam.momentree.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryImageRepository diaryImageRepository;


    /**
     * 일기 생성 메서드
     *
     * @param request 일기 생성 요청
     * @param day 일기가 작성된 날짜
     * @return 생성된 DiaryResponse
     */
    public DiaryResponse createDiary(DiaryRequest request, int day) {

        if (day < 1 || day > 31) {
            throw new BadRequestException(ExceptionType.DAY_NOT_FOUND);
        }

        if (diaryRepository.existsByDay(day)) {
            throw new BadRequestException(ExceptionType.DAY_ALREADY_EXISTS);
        }

        Diary diary = Diary.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .openStatus(request.getOpenStatus())
                .day(day)
                .build();


        Diary newDiary = diaryRepository.save(diary);


        List<DiaryImage> diaryImages = request.getDairyImageUrls().stream()
                .map(imageUrl -> DiaryImage.builder()
                        .diary(newDiary)
                        .diaryImageUrl(imageUrl)
                        .build())
                .collect(Collectors.toList());
        diaryImageRepository.saveAll(diaryImages);


        return DiaryResponse.builder()
                .id(newDiary.getId())
                .createdDate(newDiary.getCreatedDate())
                .lastModifiedDate(newDiary.getLastModifiedDate())
                .build();
    }

    public ReadDiaryResponse getDiaryByDay(int day) {
        // 유효한 day 값인지 확인
        if (day < 1 || day > 31) {
            throw new BadRequestException(ExceptionType.DAY_NOT_FOUND);
        }

        // day 값에 해당하는 다이어리 찾기
        Diary diary = diaryRepository.findByDay(day)
                .orElseThrow(() -> new BadRequestException(ExceptionType.DIARY_NOT_FOUND));

        // 다이어리 이미지 조회
        List<String> diaryImages = diaryImageRepository.findByDiary(diary).stream()
                .map(DiaryImage::getDiaryImageUrl)
                .collect(Collectors.toList());

        return ReadDiaryResponse.builder()
                .title(diary.getTitle())
                .content(diary.getContent())
                .openStatus(diary.getOpenStatus())
                .imageUrls(diaryImages)
                .day(day)
                .build();
    }

    public void assignLocationToDiary(int day, Long location) {
        if (location < 1 || location > 18) {
            throw new BadRequestException(ExceptionType.INVALID_LOCATION);
        }

        if (diaryRepository.existsByLocation(location)) {
            throw new BadRequestException(ExceptionType.LOCATION_ALREADY_USED);
        }

        Diary diary = diaryRepository.findByDay(day)
                .orElseThrow(() -> new BadRequestException(ExceptionType.DIARY_NOT_FOUND));

        diary.setLocation(location);
        diaryRepository.save(diary);
    }

    public void unassignLocation(Long location) {
        if (location < 1 || location > 18) {
            throw new BadRequestException(ExceptionType.INVALID_LOCATION);
        }

        Diary diary = diaryRepository.findByLocation(location)
                .orElseThrow(() -> new BadRequestException(ExceptionType.NO_DIARY_IN_LOCATION));

        diary.setLocation(null);
        diaryRepository.save(diary);
    }

    public List<ReadDiaryResponse> getDiariesWithoutLocation() {

        List<Diary> diaries = diaryRepository.findDiariesWithoutLocation();

        // ReadDiaryResponse로 변환하여 반환
        return diaries.stream()
                .map(diary -> ReadDiaryResponse.builder()
                        .title(diary.getTitle())
                        .content(diary.getContent())
                        .openStatus(diary.getOpenStatus())
                        .day(diary.getDay())
                        .imageUrls(diaryImageRepository.findByDiary(diary).stream()
                                .map(DiaryImage::getDiaryImageUrl)
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

}
