package tictactoe;

import tictactoe.checkers.BlockForkChecker;
import tictactoe.checkers.Checker;
import tictactoe.checkers.ForkChecker;
import tictactoe.checkers.OpponentInCornerChecker;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;

public class NextPositionHandler
{
    private final Checker forkChecker;
    private final Checker blockOpponentForkChecker;
    private final Checker opponentInOrderChecker;

    public NextPositionHandler(ForkChecker forkChecker, BlockForkChecker blockOpponentForkChecker, OpponentInCornerChecker opponentInOrderChecker)
    {
        this.forkChecker = forkChecker;
        this.blockOpponentForkChecker = blockOpponentForkChecker;
        this.opponentInOrderChecker = opponentInOrderChecker;
    }

    public Position nextMove(Board board)
    {
        if (board.hasNoEmptySpaces())
        {
            throw new IllegalArgumentException("Full board handed to NextPositionHandler. NextPositionHandler should only be invoked when game is in progress");
        }

        NextMoveResult computerCanWinNextMoveResult = board.canSeedWin(Mark.X);
        if (computerCanWinNextMoveResult.hasBeenDetermined())
        {
            return computerCanWinNextMoveResult.getNextMove();
        }

        NextMoveResult mustBlockNextMoveResult = board.canSeedWin(Mark.O);
        if (mustBlockNextMoveResult.hasBeenDetermined())
        {
            return mustBlockNextMoveResult.getNextMove();
        }

        NextMoveResult canForkNextMoveResult = forkChecker.check(board, Mark.X);
        if (canForkNextMoveResult.hasBeenDetermined())
        {
            return canForkNextMoveResult.getNextMove();
        }

        NextMoveResult blockOpponentsForkCheckNextMoveResult = blockOpponentForkChecker.check(board, Mark.X);
        if (blockOpponentsForkCheckNextMoveResult.hasBeenDetermined())
        {
            return blockOpponentsForkCheckNextMoveResult.getNextMove();
        }

        if (!board.isPositionOccupied(Position.CENTRE))
        {
            return Position.CENTRE;
        }

        NextMoveResult oppositeCornerNextMoveResult = opponentInOrderChecker.check(board, Mark.X);
        if (oppositeCornerNextMoveResult.hasBeenDetermined())
        {
            return oppositeCornerNextMoveResult.getNextMove();
        }

        Position emptyCorner = board.findEmptyCorner();
        if (emptyCorner != null)
        {
            return emptyCorner;
        }

        return board.findEmptySide();
    }
}
