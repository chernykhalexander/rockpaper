package ru.chernykh.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ru.chernykh.model.Move;
import ru.chernykh.service.PlayerMoveService;

import java.util.Queue;

@RequiredArgsConstructor
class HumanPlayerMoveServiceImpl implements PlayerMoveService {

    private final Queue<Move> scanner;

    @NonNull
    public Move getMove() {
        if (scanner.isEmpty()){
            throw new IllegalStateException("Error getting move for the player");
        }else{
            return scanner.remove();
        }

    }
}
