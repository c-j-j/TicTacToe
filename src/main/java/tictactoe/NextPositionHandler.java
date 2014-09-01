package tictactoe;

import tictactoe.checkers.Checker;

public class NextPositionHandler
{
    private final Checker forkChecker;
    private final Checker blockOpponentForkChecker;
    private final Checker opponentInOrderChecker;

    public NextPositionHandler(Checker forkChecker, Checker blockOpponentForkChecker, Checker opponentInOrderChecker)
    {
        this.forkChecker = forkChecker;
        this.blockOpponentForkChecker = blockOpponentForkChecker;
        this.opponentInOrderChecker = opponentInOrderChecker;
    }

    public Position nextMove(Board board)
    {
        NextMoveResult computerCanWinNextMoveResult = board.canSeedWin(Seed.COMPUTER);
        if (computerCanWinNextMoveResult.hasBeenDetermined())
        {
            return computerCanWinNextMoveResult.getNextMove();
        }

        NextMoveResult mustBlockNextMoveResult = board.canSeedWin(Seed.OPPONENT);
        if (mustBlockNextMoveResult.hasBeenDetermined())
        {
            return mustBlockNextMoveResult.getNextMove();
        }

        NextMoveResult canForkNextMoveResult = forkChecker.check(board, Seed.COMPUTER);
        if (canForkNextMoveResult.hasBeenDetermined())
        {
            return canForkNextMoveResult.getNextMove();
        }

        NextMoveResult blockOpponentsForkCheckNextMoveResult = blockOpponentForkChecker.check(board, Seed.COMPUTER);
        if (blockOpponentsForkCheckNextMoveResult.hasBeenDetermined())
        {
            return blockOpponentsForkCheckNextMoveResult.getNextMove();
        }

        if (!board.isPositionOccupied(Position.CENTRE))
        {
            return Position.CENTRE;
        }

        NextMoveResult oppositeCornerNextMoveResult = opponentInOrderChecker.check(board, Seed.COMPUTER);
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
