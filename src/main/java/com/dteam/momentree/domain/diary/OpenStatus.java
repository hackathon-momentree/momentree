package com.dteam.momentree.domain.diary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OpenStatus {
    OPEN("모두공개"),
    SOME("친구공개"),
    CLOSED("비공개");

    private final String openStatus;
}
