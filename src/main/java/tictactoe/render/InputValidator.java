package tictactoe.render;

import tictactoe.data.Position;

import java.util.List;

public class InputValidator
{

    public static final int MIN_INPUT_ALLOWED = 0;
    public static final int MAX_INPUT_ALLOWED = 8;

    public static boolean isValid(int userSpecifiedInteger, List<Position> emptyPositions)
    {
        return !(userSpecifiedInteger < MIN_INPUT_ALLOWED || userSpecifiedInteger > MAX_INPUT_ALLOWED) && emptyPositions.contains(Position.getPosition(userSpecifiedInteger));
    }
}
