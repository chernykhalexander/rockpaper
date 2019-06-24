package ru.chernykh.model;

import java.util.Comparator;

public class MoveComparator implements Comparator<Move> {

    @Override
    public int compare(Move move1, Move move2) {
        if (move1.equals(move2)) {
            return 0;
        }

        switch (move1) {
            case PAPER:
                switch (move2) {
                    case ROCK:
                        return 1;
                    case SCISSORS:
                        return -1;
                }
            case ROCK:
                switch (move2) {
                    case SCISSORS:
                        return 1;
                    case PAPER:
                        return -1;
                }
            case SCISSORS:
                switch (move2) {
                    case PAPER:
                        return 1;
                    case ROCK:
                        return -1;
                }
        }

        throw new IllegalStateException("System error. Could not compare moves.");
    }
}
