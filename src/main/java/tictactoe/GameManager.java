package tictactoe;

import tictactoe.checkers.Checker;

public class GameManager
{
    private final Checker winnerChecker;
    private final Checker blockChecker;
    private final Checker forkChecker;
    private final Checker blockOpponentForkChecker;

    public GameManager(Checker winnerChecker, Checker blockChecker, Checker forkChecker, Checker blockOpponentForkChecker)
    {
        this.winnerChecker = winnerChecker;
        this.blockChecker = blockChecker;
        this.forkChecker = forkChecker;
        this.blockOpponentForkChecker = blockOpponentForkChecker;
    }

    public Position nextMove(Board board)
    {
        Result result = winnerChecker.check(board, Seed.COMPUTER);
        if (result.hasBeenDetermined())
        {
            return result.getNextMove();
        }

        Result mustBlockResult = blockChecker.check(board, Seed.COMPUTER);
        if (mustBlockResult.hasBeenDetermined())
        {
            return mustBlockResult.getNextMove();
        }

        Result canForkResult = forkChecker.check(board, Seed.COMPUTER);
        if (canForkResult.hasBeenDetermined())
        {
            return canForkResult.getNextMove();
        }

        Result blockOpponentForkCheckResult = blockOpponentForkChecker.check(board, Seed.COMPUTER);
        if (blockOpponentForkCheckResult.hasBeenDetermined())
        {
            return blockOpponentForkCheckResult.getNextMove();
        }


        return Position.CENTRE;
    }
}
