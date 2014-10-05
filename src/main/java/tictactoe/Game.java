package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameProgress;
import tictactoe.data.GameState;

public class Game
{
    private final Player playerA;
    private final Player playerB;

    public Game(Player playerA, Player playerB)
    {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public GameState play(Board board)
    {
        if (board.isGameOver())
        {
            throw new IllegalArgumentException("Board provided is already in a final state.");
        }
        writeBoardToScreen(board);

        while (!board.isGameOver())
        {
            playerA.play(board);
            if (board.isGameOver())
            {
                break;
            }

            writeBoardToScreen(board);

            playerB.play(board);
            if (board.isGameOver())
            {
                break;
            }

            writeBoardToScreen(board);
        }
        writeBoardToScreen(board);
        return board.result();
    }

    private void writeBoardToScreen(Board board) {System.out.println(board);}
}
