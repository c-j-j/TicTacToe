package tictactoe.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardPositions
{
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

    public static final Set<WinningLine> WINNING_LINES = new HashSet<WinningLine>()
    {{
            add(new WinningLine(Position.TOP_LEFT, Position.TOP_CENTRE, Position.TOP_RIGHT));
            add(new WinningLine(Position.TOP_LEFT, Position.CENTRE, Position.BOTTOM_RIGHT));
            add(new WinningLine(Position.TOP_LEFT, Position.MIDDLE_LEFT, Position.BOTTOM_LEFT));
            add(new WinningLine(Position.TOP_CENTRE, Position.CENTRE, Position.BOTTOM_CENTRE));
            add(new WinningLine(Position.TOP_RIGHT, Position.CENTRE, Position.BOTTOM_LEFT));
            add(new WinningLine(Position.TOP_RIGHT, Position.MIDDLE_RIGHT, Position.BOTTOM_RIGHT));
            add(new WinningLine(Position.MIDDLE_LEFT, Position.CENTRE, Position.MIDDLE_RIGHT));
            add(new WinningLine(Position.BOTTOM_LEFT, Position.BOTTOM_CENTRE, Position.BOTTOM_RIGHT));
        }};

    public static final int POTENTIAL_WIN = 2;
}
