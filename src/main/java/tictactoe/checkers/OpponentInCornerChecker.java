package tictactoe.checkers;

import tictactoe.data.Board;
import tictactoe.data.BoardPositions;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;
import tictactoe.data.Seed;
import tictactoe.utils.PositionUtils;
import tictactoe.utils.SeedUtils;

public class OpponentInCornerChecker implements Checker
{

    /**
     * will calculate whether the opponent of seed can play in a corner, and returns a result if seed can play in the opposite corner
     */
    @Override
    public NextMoveResult check(Board board, Seed seed)
    {
        for (Position corner : BoardPositions.CORNERS)
        {
            if (board.getSeed(corner) == SeedUtils.getOtherPlayer(seed) && board.getSeed(PositionUtils.getOppositeCorner(corner)) == Seed.EMPTY)
            {
                return new NextMoveResult(PositionUtils.getOppositeCorner(corner));
            }
        }

        return NextMoveResult.indeterminateResult();
    }
}
