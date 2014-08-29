package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Board
{
    private final Map<Position, Seed> moves;
    public static final List<Position> CORNERS = new ArrayList<Position>()
    {
        {
            add(Position.TOP_RIGHT);
            add(Position.TOP_LEFT);
            add(Position.BOTTOM_RIGHT);
            add(Position.BOTTOM_LEFT);
        }
    };

    public static final List<Position> SIDES = new ArrayList<Position>()
    {
        {
            add(Position.TOP_CENTRE);
            add(Position.BOTTOM_CENTRE);
            add(Position.MIDDLE_LEFT);
            add(Position.MIDDLE_RIGHT);
        }
    };

    public Board(Map<Position, Seed> moves)
    {
        this.moves = moves;
    }


    public boolean isPositionOccupied(Position position)
    {
        return !moves.get(position).equals(Seed.EMPTY);
    }

    public Position findEmptyCorner()
    {
        for (Position corner : CORNERS)
        {
            if (moves.get(corner) == Seed.EMPTY)
            {
                return corner;
            }
        }
        return null;
    }

    public Position findEmptySide()
    {
        for (Position side : SIDES)
        {
            if (moves.get(side) == Seed.EMPTY)
            {
                return side;
            }
        }
        return null;
    }
}
