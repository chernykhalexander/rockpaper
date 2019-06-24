package ru.chernykh.service.impl;

import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.chernykh.model.GameState;
import ru.chernykh.model.Move;
import ru.chernykh.model.MoveComparator;
import ru.chernykh.model.Turn;
import ru.chernykh.service.StatisticsService;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceImplTest {

    @Mock
    GameState gameState;

    StatisticsService statisticsService;

    @Before
    public void before() {
        statisticsService = new StatisticsServiceImpl(new MoveComparator());
    }

    @Test
    public void shouldCalculateStatistics() {
        val list = Arrays.asList(
                new Turn(Move.PAPER, Move.PAPER),
                new Turn(Move.ROCK, Move.ROCK),
                new Turn(Move.SCISSORS, Move.SCISSORS),
                new Turn(Move.PAPER, Move.ROCK),
                new Turn(Move.PAPER, Move.SCISSORS),
                new Turn(Move.ROCK, Move.PAPER),
                new Turn(Move.ROCK, Move.SCISSORS),
                new Turn(Move.SCISSORS, Move.ROCK),
                new Turn(Move.SCISSORS, Move.PAPER));

        when(gameState.getTurnResultList()).thenReturn(list);

        val statistics = statisticsService.calculateStatistics(gameState);

        assertThat(statistics.getDraws()).isEqualTo(3);
        assertThat(statistics.getLost()).isEqualTo(3);
        assertThat(statistics.getTotalGamesPlayed()).isEqualTo(list.size());
        assertThat(statistics.getWon()).isEqualTo(3);
    }
}
