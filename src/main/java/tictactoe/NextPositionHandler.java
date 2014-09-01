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
        Result computerCanWinResult = board.canSeedWin(Seed.COMPUTER);
        if (computerCanWinResult.hasBeenDetermined())
        {
            return computerCanWinResult.getNextMove();
        }

        Result mustBlockResult = board.canSeedWin(Seed.OPPONENT);
        if (mustBlockResult.hasBeenDetermined())
        {
            return mustBlockResult.getNextMove();
        }

        Result canForkResult = forkChecker.check(board, Seed.COMPUTER);
        if (canForkResult.hasBeenDetermined())
        {
            return canForkResult.getNextMove();
        }

        Result blockOpponentsForkCheckResult = blockOpponentForkChecker.check(board, Seed.COMPUTER);
        if (blockOpponentsForkCheckResult.hasBeenDetermined())
        {
            return blockOpponentsForkCheckResult.getNextMove();
        }

        if (!board.isPositionOccupied(Position.CENTRE))
        {
            return Position.CENTRE;
        }

        Result oppositeCornerResult = opponentInOrderChecker.check(board, Seed.COMPUTER);
        if (oppositeCornerResult.hasBeenDetermined())
        {
            return oppositeCornerResult.getNextMove();
        }

        Position emptyCorner = board.findEmptyCorner();
        if (emptyCorner != null)
        {
            return emptyCorner;
        }

        return board.findEmptySide();
    }
}
