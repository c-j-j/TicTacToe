package tictactoe;

import java.util.HashMap;
import java.util.Map;

public class BoardBuilder
{
    Map<Position, Seed> moves = new HashMap<>();

    public BoardBuilder()
    {
        constructEmptyBoard();
    }

    private void constructEmptyBoard()
    {
        moves.put(Position.TOP_RIGHT, Seed.EMPTY);
        moves.put(Position.TOP_CENTRE, Seed.EMPTY);
        moves.put(Position.TOP_LEFT, Seed.EMPTY);
        moves.put(Position.MIDDLE_RIGHT, Seed.EMPTY);
        moves.put(Position.CENTRE, Seed.EMPTY);
        moves.put(Position.MIDDLE_LEFT, Seed.EMPTY);
        moves.put(Position.BOTTOM_RIGHT, Seed.EMPTY);
        moves.put(Position.BOTTOM_CENTRE, Seed.EMPTY);
        moves.put(Position.BOTTOM_LEFT, Seed.EMPTY);
    }

    public BoardBuilder withMove(Position position, Seed seed)
    {
        moves.put(position, seed);
        return this;
    }

    public Board build()
    {
        return new Board(moves);
    }
}
