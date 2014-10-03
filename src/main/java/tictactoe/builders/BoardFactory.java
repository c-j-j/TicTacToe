package tictactoe.builders;

import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public class BoardFactory
{
    public static Board addMove(Board board, Position newPosition, Mark mark)
    {
        if (!(board.getMoves().get(newPosition) == Mark.EMPTY))
        {
            throw new IllegalArgumentException(String.format("Board position %s already occupied.", newPosition.name()));
        }

        return new BoardBuilder().withBoard(board).withMove(newPosition, mark).build();
    }

    public static Board emptyBoard()
    {
        return new BoardBuilder().build();
    }
}
