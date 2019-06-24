package ru.chernykh.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.chernykh.model.Move;
import ru.chernykh.service.PlayerMoveService;

import java.util.Random;

@RequiredArgsConstructor
class ComputerPlayerMoveServiceImpl implements PlayerMoveService {

    private final Random random;

    @Override
    public Move getMove() {

        val values = Move.values();

        val ordinal = random.nextInt(values.length);

        return values[ordinal];
    }
}
