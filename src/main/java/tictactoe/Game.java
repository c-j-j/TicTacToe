package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameProgress;

public class Game
{
    private final Player playerA;
    private final Player playerB;

    public Game(Player playerA, Player playerB)
    {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public GameProgress play(Board board)
    {
        if (board.isGameOver())
        {
            throw new IllegalArgumentException("Board provided is already in a final state.");
        }

        while (!board.isGameOver())
        {
            playerA.play(board);
            if (board.isGameOver())
            {
                break;
            }

            playerB.play(board);
            if (board.isGameOver())
            {
                break;
            }
        }

        return board.result();
    }
}
