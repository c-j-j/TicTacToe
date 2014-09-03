package tictactoe.builders;

import tictactoe.data.Board;
import tictactoe.data.Position;
import tictactoe.data.Seed;

public class BoardFactory
{
    public static Board addMove(Board board, Position newPosition, Seed seed)
    {
        if (!(board.getMoves().get(newPosition) == Seed.EMPTY))
        {
            throw new IllegalArgumentException(String.format("Board position %s already occupied.", newPosition.name()));
        }

        return new BoardBuilder().withBoard(board).withMove(newPosition, seed).build();
    }

    public static Board emptyBoard()
    {
        return new BoardBuilder().build();
    }
}
