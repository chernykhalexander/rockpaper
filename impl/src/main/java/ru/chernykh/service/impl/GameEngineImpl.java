package ru.chernykh.service.impl;

import lombok.NonNull;
import lombok.val;
import ru.chernykh.model.GameState;
import ru.chernykh.model.PlayerNumber;
import ru.chernykh.model.Turn;
import ru.chernykh.service.GameService;


class GameEngineImpl implements GameService {


    @Override
    public Turn makeTurn(@NonNull GameState gameState) {
        val moveForPlayer1 = gameState.getMoveForPlayer(PlayerNumber.FIRST);
        val moveForPlayer2 = gameState.getMoveForPlayer(PlayerNumber.SECOND);

        val turn = new Turn(moveForPlayer1, moveForPlayer2);
        gameState.addTurnToHistory(turn);

        return turn;
    }
}
