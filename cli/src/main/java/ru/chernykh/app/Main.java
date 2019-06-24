package ru.chernykh.app;

import asg.cliche.Command;
import asg.cliche.ShellFactory;
import lombok.NonNull;
import lombok.val;
import ru.chernykh.model.GameStatus;
import ru.chernykh.model.Move;
import ru.chernykh.service.GameLifecycle;
import ru.chernykh.service.impl.GameLifecycleFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Main {

    private GameLifecycle gameLifecycle;
    private final Queue<Move> moveQueue = new LinkedList<>();



    private static final String GAME_STARTED = "Game started";
    private static final String GAME_HAS_ALREADY_BEEN_STOPPED = "game has already been stopped";
    private static final String GAME_STOPPED = "game stopped";
    private static final String NOTHING_TO_STOP = "nothing to stop";
    private static final String YOU_HAVE_NOT_YET_STARTED_ANY_GAME = "You have not yet started any game";
    private static final String YOU_NEED_TO_STOP_THE_GAME_BEFORE_YOU_CAN_VIEW_STATISTICS = "You need to stop the game before you can view statistics";
    private static final String DEFAULT_ERROR_MESSAGE = "You should start the game before making any turns";
    private static final String YOU_NEED_TO_STOP_THE_GAME_BEFORE_YOU_CAN_START_AGAIN = "You need to stop the game before you can start again";


    @Command(name = "start", abbrev = "start", description = "Start our game!")
    public String startGame() {

        if (Objects.nonNull(gameLifecycle) && gameLifecycle.currentStatus().equals(GameStatus.STARTED)) {
            return YOU_NEED_TO_STOP_THE_GAME_BEFORE_YOU_CAN_START_AGAIN;
        }

        gameLifecycle = GameLifecycleFactory.getGameLifecycle(moveQueue);

        gameLifecycle.start();

        return GAME_STARTED;
    }



    @Command(name = "paper", abbrev = "p", description = "Paper")
    public String paper() {
        return makeTurn(Move.PAPER);
    }

    @Command(name = "rock", abbrev = "r", description = "ROCK!!")
    public String rock() {
        return makeTurn(Move.ROCK);
    }

    @Command(name = "scissors", abbrev = "sc", description = "Scissors")
    public String scissors() {
        return makeTurn(Move.SCISSORS);
    }

    private String makeTurn(@NonNull Move move) {
        if (Objects.nonNull(gameLifecycle) && gameLifecycle.currentStatus().equals(GameStatus.STARTED)) {
            moveQueue.add(move);
            val turn = gameLifecycle.makeTurn();
            return String.format("You=%s Opponent=%s", turn.getResult1(), turn.getResult2());
        } else {
            return Main.DEFAULT_ERROR_MESSAGE;
        }
    }

    @Command(name = "stop", abbrev = "stop", description = "Time to rest")
    public String stopGame() {
        if (Objects.nonNull(gameLifecycle)) {

            if (gameLifecycle.currentStatus().equals(GameStatus.TERMINATED)) {
                return GAME_HAS_ALREADY_BEEN_STOPPED;
            }

            gameLifecycle.stop();
            moveQueue.clear();
            return GAME_STOPPED;
        } else {
            return NOTHING_TO_STOP;
        }

    }

    @Command(name = "getstat", abbrev = "gs", description = "Get info about your WINNINGS!")
    public String getStatistics() {
        if (Objects.isNull(gameLifecycle)) {
            return YOU_HAVE_NOT_YET_STARTED_ANY_GAME;
        }

        if (gameLifecycle.currentStatus().equals(GameStatus.TERMINATED)) {
            return gameLifecycle.getStatistics().toString();
        } else {
            return YOU_NEED_TO_STOP_THE_GAME_BEFORE_YOU_CAN_VIEW_STATISTICS;
        }

    }

    public static void main(String[] args) throws IOException {
        ShellFactory.createConsoleShell("rockpaper", "Enter '?list' to list all commands", new Main())
                .commandLoop();
    }
}
