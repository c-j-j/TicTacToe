package tictactoe.checkers;

import tictactoe.Board;
import tictactoe.Position;
import tictactoe.Result;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;

import java.util.ArrayList;
import java.util.List;

public class ForkChecker implements Checker {
    public static final int MIN_REQUIRED_FOR_FORK = 2;


    public Result check(Board board, Seed seed) {
        List<Position> forkingPositions = new ArrayList<>();

        for (Position emptyPosition : board.getEmptyPositions()) {
            Board boardWithAdditionalMove = new BoardBuilder()
                    .withBoard(board)
                    .withMove(emptyPosition, Seed.COMPUTER)
                    .build();

            Result result = boardWithAdditionalMove.canSeedWin(Seed.COMPUTER);

            if (result.getNextMoves().size() >= MIN_REQUIRED_FOR_FORK) {
                forkingPositions.add(emptyPosition);
            }
        }

        return new Result(forkingPositions);
    }
}
