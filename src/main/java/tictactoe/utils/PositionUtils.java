package tictactoe.utils;

import tictactoe.data.Position;

import java.util.HashMap;
import java.util.Map;

public class PositionUtils
{
    public static final Map<Position, Position> oppositeCorners = new HashMap<Position, Position>()
    {{
            put(Position.TOP_RIGHT, Position.BOTTOM_LEFT);
            put(Position.TOP_LEFT, Position.BOTTOM_RIGHT);
            put(Position.BOTTOM_LEFT, Position.TOP_RIGHT);
            put(Position.BOTTOM_RIGHT, Position.TOP_LEFT);
        }};

    public static Position getOppositeCorner(Position corner)
    {
        return oppositeCorners.get(corner);
    }
}
