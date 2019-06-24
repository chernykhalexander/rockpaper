package ru.chernykh.service;

import ru.chernykh.model.GameState;
import ru.chernykh.model.Turn;

public interface GameService {
    Turn makeTurn(GameState gameState);
}
