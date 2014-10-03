package tictactoe.builders;

import tictactoe.data.Board;
import tictactoe.data.Position;
import tictactoe.data.Mark;

import java.util.HashMap;
import java.util.Map;

public class BoardBuilder
{
    Map<Position, Mark> moves = new HashMap<>();

    public BoardBuilder()
    {
        constructEmptyBoard();
    }

    private void constructEmptyBoard()
    {
        moves.put(Position.TOP_RIGHT, Mark.EMPTY);
        moves.put(Position.TOP_CENTRE, Mark.EMPTY);
        moves.put(Position.TOP_LEFT, Mark.EMPTY);
        moves.put(Position.MIDDLE_RIGHT, Mark.EMPTY);
        moves.put(Position.CENTRE, Mark.EMPTY);
        moves.put(Position.MIDDLE_LEFT, Mark.EMPTY);
        moves.put(Position.BOTTOM_RIGHT, Mark.EMPTY);
        moves.put(Position.BOTTOM_CENTRE, Mark.EMPTY);
        moves.put(Position.BOTTOM_LEFT, Mark.EMPTY);
    }

    public BoardBuilder withMove(Position position, Mark mark)
    {
        moves.put(position, mark);
        return this;
    }

    public Board build()
    {
        return new Board(moves);
    }

    public BoardBuilder withBoard(Board board)
    {
        moves = new HashMap<>(board.getMoves());
        return this;
    }
}
