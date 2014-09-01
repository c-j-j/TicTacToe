package tictactoe;

public class GameStateManager
{
    public static final int EMPTY_BOARD = 0;

    public GameState getState(Board board)
    {
        if (board.hasSeedWon(Seed.COMPUTER))
        {
            return GameState.COMPUTER_WINS;
        }
        else if (board.hasSeedWon(Seed.OPPONENT))
        {
            return GameState.COMPUTER_LOSES;
        }
        else if (isBoardEmpty(board))
        {
            return GameState.DRAW;
        }
        else
        {
            return GameState.IN_PROGRESS;
        }
    }

    private boolean isBoardEmpty(Board board)
    {
        return board.getEmptyPositions().size() == EMPTY_BOARD;
    }
}
