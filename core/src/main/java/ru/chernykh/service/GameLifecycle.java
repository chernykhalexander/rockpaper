package ru.chernykh.service;

import ru.chernykh.model.GameStatistics;
import ru.chernykh.model.GameStatus;
import ru.chernykh.model.Turn;

public interface GameLifecycle {

    GameStatus start();

    GameStatus stop();

    GameStatus currentStatus();

    Turn makeTurn();

    GameStatistics getStatistics();
}
