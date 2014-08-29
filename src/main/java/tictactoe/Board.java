package tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Board
{
    private final Map<Position, Seed> positions;

    public Board(Map<Position, Seed> positions)
    {
        this.positions = positions;
    }

    public static Board newBoard()
    {
        return new Board(new HashMap<Position, Seed>(){{
        }});
    }
}
