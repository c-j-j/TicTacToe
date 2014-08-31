package tictactoe.checkers;

import tictactoe.Board;
import tictactoe.Position;
import tictactoe.Result;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;
import tictactoe.utils.CountingMap;

import java.util.List;

public class ForkChecker implements Checker
{
    public static final int MIN_REQUIRED_FOR_FORK = 2;
    private Checker winnerChecker;

    public ForkChecker(Checker winnerChecker) {

        this.winnerChecker = winnerChecker;
    }

    public Result check(Board board, Seed seed)
    {
        List<Position> emptyPositions = board.getEmptyPositions();
        CountingMap<Position> countingMap = new CountingMap<>();

        for (Position emptyPosition : emptyPositions) {
            Board boardWithAdditionalMove = new BoardBuilder()
                    .withBoard(board)
                    .withMove(emptyPosition, Seed.COMPUTER)
                    .build();

            Result result =  winnerChecker.check(boardWithAdditionalMove, Seed.COMPUTER);

            if (result.hasBeenDetermined()) {
                countingMap.add(emptyPosition);
            }
        }

        if(countingMap.countOfMostCommonElement()>= MIN_REQUIRED_FOR_FORK) {
            return new Result(countingMap.mostCommonElement());
        }else {
            return Result.indeterminateResult();
        }
    }
}
