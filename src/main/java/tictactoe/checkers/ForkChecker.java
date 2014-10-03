package tictactoe.checkers;

import tictactoe.builders.BoardFactory;
import tictactoe.data.Board;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;
import tictactoe.data.Mark;

import java.util.ArrayList;
import java.util.List;

public class ForkChecker implements Checker
{
    public static final int MIN_REQUIRED_FOR_FORK = 2;


    public NextMoveResult check(Board board, Mark mark)
    {
        List<Position> forkingPositions = new ArrayList<>();

        for (Position emptyPosition : board.getEmptyPositions())
        {
            Board boardWithAdditionalMove = BoardFactory.addMove(board, emptyPosition, mark);

            NextMoveResult nextMoveResult = boardWithAdditionalMove.canSeedWin(mark);

            if (nextMoveResult.getNextMoves().size() >= MIN_REQUIRED_FOR_FORK)
            {
                forkingPositions.add(emptyPosition);
            }
        }

        return new NextMoveResult(forkingPositions);
    }
}
