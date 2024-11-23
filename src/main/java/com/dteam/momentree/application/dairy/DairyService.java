package com.dteam.momentree.application.dairy;

import com.dteam.momentree.domain.dairy.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DairyService {

    private final DiaryRepository diaryRepository;



}
