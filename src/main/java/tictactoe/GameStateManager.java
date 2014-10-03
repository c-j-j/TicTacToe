package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameState;
import tictactoe.data.Mark;

public class GameStateManager
{
    public GameState getState(Board board)
    {
        if (board.hasSeedWon(Mark.X))
        {
            return GameState.COMPUTER_WINS;
        }
        else if (board.hasSeedWon(Mark.O))
        {
            return GameState.COMPUTER_LOSES;
        }
        else if (board.hasNoEmptySpaces())
        {
            return GameState.DRAW;
        }
        else
        {
            return GameState.IN_PROGRESS;
        }
    }
}
