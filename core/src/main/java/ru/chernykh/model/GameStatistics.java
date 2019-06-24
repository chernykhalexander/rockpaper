package ru.chernykh.model;

import lombok.Data;

@Data
public class GameStatistics {
    private final int totalGamesPlayed;
    private final int won;
    private final int lost;
    private final int draws;
}
