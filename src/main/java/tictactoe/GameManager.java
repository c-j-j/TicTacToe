package tictactoe;

public class GameManager
{
    private final WinnerChecker winnerChecker;

    public GameManager(WinnerChecker winnerChecker)
    {
        this.winnerChecker = winnerChecker;
    }

    public Position nextMove(Board board)
    {
        Result result = winnerChecker.canPlayerWin(board, Seed.COMPUTER);

        if (result.hasBeenDetermined())
        {
            return result.getNextMove();
        }
        return Position.CENTRE;
    }
}
