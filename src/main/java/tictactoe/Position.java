package tictactoe;

public enum Position
{
    TOP_LEFT(0), TOP_CENTRE(1), TOP_RIGHT(2),
    MIDDLE_RIGHT(4), CENTRE(5), MIDDLE_LEFT(6),
    BOTTOM_RIGHT(7), BOTTOM_CENTRE(8), BOTTOM_LEFT(9);

    private final int arrayPosition;

    Position(int arrayPosition)
    {
        this.arrayPosition = arrayPosition;
    }
}
