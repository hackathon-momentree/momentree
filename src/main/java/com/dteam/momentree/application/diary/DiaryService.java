package com.dteam.momentree.application.diary;

import com.dteam.momentree.api.diary.dto.DiaryRequest;
import com.dteam.momentree.api.diary.dto.DiaryResponse;
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
     * @param request 다이어리 요청 데이터
     * @return 생성된 다이어리의 응답 데이터
     */
    public DiaryResponse createDiary(DiaryRequest request) {

        Diary diary = Diary.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .openStatus(request.getOpenStatus())
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



}
