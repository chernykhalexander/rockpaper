package ru.chernykh.service;

import ru.chernykh.model.GameState;
import ru.chernykh.model.GameStatistics;

public interface StatisticsService {
    GameStatistics calculateStatistics(GameState gameState);
}
