package ru.chernykh.service.impl;

import lombok.Data;
import lombok.NonNull;
import lombok.val;
import ru.chernykh.model.GameState;
import ru.chernykh.model.GameStatistics;
import ru.chernykh.model.Move;
import ru.chernykh.model.Turn;
import ru.chernykh.service.StatisticsService;

import java.util.Comparator;

@Data
class StatisticsServiceImpl implements StatisticsService {

    private final Comparator<Move> moveComparator;

    @Override
    public GameStatistics calculateStatistics(@NonNull GameState gameState) {

        val resultList = gameState.getTurnResultList();

        int wons = 0;
        int lost = 0;
        int draws = 0;
        for (Turn turn : resultList) {
            val compareResult = moveComparator.compare(turn.getResult1(), turn.getResult2());
            switch (compareResult) {
                case 0: {
                    draws++;
                    break;
                }
                case -1: {
                    lost++;
                    break;
                }
                case 1: {
                    wons++;
                    break;
                }
            }
        }

        return new GameStatistics(resultList.size(), wons, lost, draws);
    }
}
