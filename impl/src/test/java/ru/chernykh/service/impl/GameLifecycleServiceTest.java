package ru.chernykh.service.impl;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.chernykh.model.*;
import ru.chernykh.service.GameService;
import ru.chernykh.service.StatisticsService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameLifecycleServiceTest {

    @Mock
    GameService gameEngine;

    @InjectMocks
    GameLifecycleServiceImpl gameService;

    @Mock
    GameState gameState;

    @Mock
    StatisticsService statisticsEngine;

    @Test
    public void shouldStartGameWhenInInitializedState() {
        val start = gameService.start();

        assertThat(start).isEqualTo(GameStatus.STARTED);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotStartGameTwice() {

        gameService.start();
        gameService.start();
    }

    @Test
    public void shouldCheckInitialGameState() {
        assertThat(gameService.getCurrentStatus()).isEqualTo(GameStatus.INITIALISED);
    }

    @Test
    public void shouldStopGame() {
        gameService.stop();

        assertThat(gameService.getCurrentStatus()).isEqualTo(GameStatus.TERMINATED);

    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotMakeTurnWhenNotStarted() {
        gameService.makeTurn();
    }

    @Test
    public void shouldMakeATurn() {
        when(gameEngine.makeTurn(any(GameState.class))).thenReturn(new Turn(Move.ROCK, Move.PAPER));

        gameService.start();
        gameService.makeTurn();

        verify(gameEngine).makeTurn(gameState);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldNotMakeTurnWhenNotInStartedState() {
        gameService.makeTurn();
    }

    @Test
    public void shouldGetStatistics() {
        when(statisticsEngine.calculateStatistics(any(GameState.class))).thenReturn(any(GameStatistics.class));

        gameService.start();
        gameService.stop();
        gameService.getStatistics();

        verify(statisticsEngine).calculateStatistics(any(GameState.class));
    }

}
