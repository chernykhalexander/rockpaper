package ru.chernykh.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PlayerNumber {
    FIRST(0), SECOND(1);

    @Getter
    private final int number;

}
