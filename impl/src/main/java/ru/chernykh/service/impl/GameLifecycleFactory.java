package ru.chernykh.service.impl;

import lombok.NonNull;
import ru.chernykh.model.GameState;
import ru.chernykh.model.Move;
import ru.chernykh.model.MoveComparator;
import ru.chernykh.service.GameLifecycle;
import ru.chernykh.service.GameService;

import java.util.Arrays;
import java.util.Queue;
import java.util.Random;

public class GameLifecycleFactory {
    public static GameLifecycle getGameLifecycle(@NonNull Queue<Move> moveQueue) {
        final GameService gameEngine = new GameEngineImpl();
        final Random random = new Random();

        return new GameLifecycleServiceImpl(
                gameEngine,
                new GameState(
                        Arrays.asList(
                                new HumanPlayerMoveServiceImpl(moveQueue),
                                new ComputerPlayerMoveServiceImpl(random)
                        )
                ),
                new StatisticsServiceImpl(new MoveComparator())
        );
    }
}
