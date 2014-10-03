package tictactoe.checkers;

import tictactoe.data.*;
import tictactoe.data.Mark;
import tictactoe.utils.MarkUtils;
import tictactoe.utils.PositionUtils;

public class OpponentInCornerChecker implements Checker
{

    /**
     * will calculate whether the opponent of mark can play in a corner, and returns a result if mark can play in the opposite corner
     */
    @Override
    public NextMoveResult check(Board board, Mark mark)
    {
        for (Position corner : BoardPositions.CORNERS)
        {
            if (board.getSeed(corner) == MarkUtils.getOtherMark(mark) && board.getSeed(PositionUtils.getOppositeCorner(corner)) == Mark.EMPTY)
            {
                return new NextMoveResult(PositionUtils.getOppositeCorner(corner));
            }
        }

        return NextMoveResult.indeterminateResult();
    }
}
