package ru.chernykh.model;

import java.util.Arrays;
import java.util.Optional;

public enum Move {
    ROCK {
        @Override
        public String shortName() {
            return "R";
        }
    },
    SCISSORS {
        @Override
        public String shortName() {
            return "S";
        }
    },
    PAPER {
        @Override
        public String shortName() {
            return "P";
        }
    };

    public abstract String shortName();

    public static Optional<Move> findByShortName(String shortName) {
        return Arrays.stream(values()).filter(p -> p.shortName().equalsIgnoreCase(shortName)).findFirst();
    }
}
