package ru.chernykh.service.impl;

import lombok.val;
import org.junit.Test;
import ru.chernykh.model.Move;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class ComputerPlayerMoveServiceImplTest {

    @Test
    public void shouldProduceARockTurn(){
        val seed = 1;
        shouldProduceGivenOutcome(new Random(seed),Move.ROCK );
    }

    @Test
    public void shouldProduceAPaperTurn(){
        val seed = 3;
        shouldProduceGivenOutcome(new Random(seed),Move.PAPER);
    }

    @Test
    public void shouldProduceAScissorsTurn(){
        val seed = 2;
        shouldProduceGivenOutcome(new Random(seed), Move.SCISSORS);
    }

    private void shouldProduceGivenOutcome(Random random, Move outcome){
        val computerPlayerMoveServiceImpl = new ComputerPlayerMoveServiceImpl(random);

        val move = computerPlayerMoveServiceImpl.getMove();

        assertThat(move).isEqualTo(outcome);
    }
}
