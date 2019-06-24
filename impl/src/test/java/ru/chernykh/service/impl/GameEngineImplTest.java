package ru.chernykh.service.impl;

import lombok.val;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ru.chernykh.model.GameState;
import ru.chernykh.model.Move;
import ru.chernykh.model.PlayerNumber;
import ru.chernykh.model.Turn;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GameEngineImplTest {

    @Mock
    GameState gameState;

    @Test
    public void shouldMakeATurn(){
        val gameEngine = new GameEngineImpl();

        when(gameState.getMoveForPlayer(PlayerNumber.FIRST)).thenReturn(Move.PAPER);
        when(gameState.getMoveForPlayer(PlayerNumber.SECOND)).thenReturn(Move.PAPER);
        doNothing().when(gameState).addTurnToHistory(any(Turn.class));

        gameEngine.makeTurn(gameState);

        verify(gameState).getMoveForPlayer(PlayerNumber.FIRST);
        verify(gameState).getMoveForPlayer(PlayerNumber.SECOND);
        verify(gameState).addTurnToHistory(any(Turn.class));
    }
}
