package ru.chernykh.model;

import lombok.NonNull;
import ru.chernykh.service.PlayerMoveService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameState {
    private static final int MAXIMUM_NUMBER_OF_PLAYERS = 2;

    public GameState(@NonNull List<PlayerMoveService> playerMoveServiceList) {
        checkArguments(playerMoveServiceList);
        this.playerMoveServiceList = playerMoveServiceList;
    }

    private void checkArguments(List<PlayerMoveService> playerMoveServiceList) {
        if (playerMoveServiceList.size() != MAXIMUM_NUMBER_OF_PLAYERS) {
            throw new IllegalArgumentException("Only 2 players supported at the moment");
        }
    }

    private final List<PlayerMoveService> playerMoveServiceList;

    private final List<Turn> turnResultList = new ArrayList<>();


    public Move getMoveForPlayer(PlayerNumber playerNumber) {
        return playerMoveServiceList.get(playerNumber.getNumber()).getMove();
    }

    public void addTurnToHistory(@NonNull Turn turn){
        turnResultList.add(turn);
    }

    public List<Turn> getTurnResultList(){
        return Collections.unmodifiableList(turnResultList);
    }
}
