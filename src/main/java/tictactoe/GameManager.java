package tictactoe;

import tictactoe.checkers.BlockChecker;
import tictactoe.checkers.WinnerChecker;

public class GameManager
{
    private final WinnerChecker winnerChecker;
    private final BlockChecker blockChecker;

    public GameManager(WinnerChecker winnerChecker, BlockChecker blockChecker)
    {
        this.winnerChecker = winnerChecker;
        this.blockChecker = blockChecker;
    }

    public Position nextMove(Board board)
    {
        Result result = winnerChecker.canPlayerWin(board, Seed.COMPUTER);
        if (result.hasBeenDetermined())
        {
            return result.getNextMove();
        }

        Result mustBlockResult = blockChecker.mustBlock(board, Seed.COMPUTER);
        if (mustBlockResult.hasBeenDetermined())
        {
            return mustBlockResult.getNextMove();
        }


        return Position.CENTRE;
    }
}
