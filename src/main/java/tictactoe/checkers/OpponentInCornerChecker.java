package tictactoe.checkers;

import tictactoe.Board;
import tictactoe.NextMoveResult;
import tictactoe.Position;
import tictactoe.Seed;
import tictactoe.utils.PositionUtils;
import tictactoe.utils.SeedUtils;

public class OpponentInCornerChecker implements Checker
{

    /**
     * seed - represents opponent which you'd like to place in the opposite corner
     */
    @Override
    public NextMoveResult check(Board board, Seed seed)
    {
        for (Position corner : Board.CORNERS)
        {
            if (board.getSeed(corner) == SeedUtils.getOtherPlayer(seed) && board.getSeed(PositionUtils.getOppositeCorner(corner)) == Seed.EMPTY)
            {
                return new NextMoveResult(PositionUtils.getOppositeCorner(corner));
            }
        }

        return NextMoveResult.indeterminateResult();
    }
}
