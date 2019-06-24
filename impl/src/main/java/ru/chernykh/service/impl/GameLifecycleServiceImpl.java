package ru.chernykh.service.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.chernykh.model.GameState;
import ru.chernykh.model.GameStatistics;
import ru.chernykh.model.GameStatus;
import ru.chernykh.model.Turn;
import ru.chernykh.service.GameLifecycle;
import ru.chernykh.service.GameService;
import ru.chernykh.service.StatisticsService;

@RequiredArgsConstructor
class GameLifecycleServiceImpl implements GameLifecycle {

    @Getter
    private GameStatus currentStatus = GameStatus.INITIALISED;

    private final GameService gameEngine;

    private final GameState gameState;

    private final StatisticsService statisticsEngine;

    @Override
    public GameStatus start() {
        if (currentStatus.equals(GameStatus.INITIALISED)) {
            return currentStatus = GameStatus.STARTED;
        } else {
            throw new IllegalStateException("Game shouldn't be restarted");
        }
    }

    @Override
    public GameStatus stop() {
        return currentStatus = GameStatus.TERMINATED;
    }

    @Override
    public Turn makeTurn() {
        if (currentStatus != GameStatus.STARTED) {
            throw new IllegalStateException("You can make a turn only if the game has been started");
        }

        return gameEngine.makeTurn(gameState);

    }

    @Override
    public GameStatistics getStatistics() {
        if (currentStatus != GameStatus.TERMINATED) {
            throw new IllegalStateException("Can only get statistics if the game has been terminated");
        }

        return statisticsEngine.calculateStatistics(gameState);
    }

    @Override
    public GameStatus currentStatus() {
        return currentStatus;
    }
}
