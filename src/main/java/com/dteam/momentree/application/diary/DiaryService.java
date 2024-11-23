package com.dteam.momentree.application.diary;

import com.dteam.momentree.api.diary.DiaryController;
import com.dteam.momentree.api.diary.dto.DiaryRequest;
import com.dteam.momentree.api.diary.dto.DiaryResponse;
import com.dteam.momentree.api.diary.dto.DiaryUpdateRequest;
import com.dteam.momentree.api.diary.dto.ReadDiaryResponse;
import com.dteam.momentree.application.config.exception.BadRequestException;
import com.dteam.momentree.application.config.exception.ExceptionType;
import com.dteam.momentree.domain.diary.Diary;
import com.dteam.momentree.domain.diary.DiaryRepository;
import io.micrometer.common.util.StringUtils;
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

    public DiaryResponse createDiary(DiaryRequest request, int day, Long userId) {
        if(request.getContent() == null){
            throw new BadRequestException(ExceptionType.INVALID_INPUT);
        }
        if (day < 1 || day > 31) {
            throw new BadRequestException(ExceptionType.DAY_NOT_FOUND);
        }

        if (diaryRepository.existsByCreateUserAndDay(userId, day)) {
            throw new BadRequestException(ExceptionType.DAY_ALREADY_EXISTS);

        }

        Diary diary = Diary.builder()
                .content(request.getContent())
                .openStatus(request.getOpenStatus())
                .day(day)
                .build();

        Diary newDiary = diaryRepository.save(diary);

        return DiaryResponse.builder()
                .id(newDiary.getId())
                .createdDate(newDiary.getCreatedDate())
                .lastModifiedDate(newDiary.getLastModifiedDate())
                .build();
    }

    public ReadDiaryResponse getDiaryByDay(Long userId, int day) {
        if (day < 1 || day > 31) {
            throw new BadRequestException(ExceptionType.DAY_NOT_FOUND);
        }

        Diary diary = diaryRepository.findByCreateUserAndDay(userId, day)
                .orElseThrow(() -> new BadRequestException(ExceptionType.DIARY_NOT_FOUND));

        return ReadDiaryResponse.builder()
                .content(diary.getContent())
                .openStatus(diary.getOpenStatus())
                .day(day)
                .build();
    }

    public void assignLocationToDiary(int day, Long location, Long userId) {
        if (location < 1 || location > 18) {
            throw new BadRequestException(ExceptionType.INVALID_LOCATION);
        }

        if (diaryRepository.existsByCreateUserAndLocation(userId, location)) {
            throw new BadRequestException(ExceptionType.LOCATION_ALREADY_USED);
        }

        Diary diary = diaryRepository.findByCreateUserAndDay(userId, day)
                .orElseThrow(() -> new BadRequestException(ExceptionType.DIARY_NOT_FOUND));

        diary.setLocation(location);
        diaryRepository.save(diary);
    }

    public void unassignLocation(Long location, Long userId) {
        if (location < 1 || location > 18) {
            throw new BadRequestException(ExceptionType.INVALID_LOCATION);
        }

        Diary diary = diaryRepository.findByCreateUserAndLocation(userId, location)
                .orElseThrow(() -> new BadRequestException(ExceptionType.NO_DIARY_IN_LOCATION));

        diary.setLocation(null);
        diaryRepository.save(diary);
    }

    public List<ReadDiaryResponse> getDiariesWithoutLocation(Long userId) {
        List<Diary> diaries = diaryRepository.findDiariesWithoutLocationByUser(userId);

        return diaries.stream()
                .map(diary -> ReadDiaryResponse.builder()
                        .content(diary.getContent())
                        .openStatus(diary.getOpenStatus())
                        .day(diary.getDay())
                        .build())
                .collect(Collectors.toList());
    }

    public void update(Long userId, DiaryUpdateRequest updateRequest) {
        if(StringUtils.isEmpty(updateRequest.getContent())) throw new BadRequestException(ExceptionType.INVALID_INPUT);
        Diary diary = diaryRepository.findById(updateRequest.getId())
                .orElseThrow(() -> new BadRequestException(ExceptionType.DIARY_NOT_FOUND));
        if(!userId.equals(diary.getCreateUser())) throw new BadRequestException(ExceptionType.FORBIDDEN);
        diary.updateContent(updateRequest.getContent());
    }
}

