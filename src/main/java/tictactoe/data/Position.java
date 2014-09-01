package tictactoe.data;

public enum Position
{
    TOP_LEFT(0), TOP_CENTRE(1), TOP_RIGHT(2),
    MIDDLE_LEFT(3), CENTRE(4), MIDDLE_RIGHT(5),
    BOTTOM_LEFT(6), BOTTOM_CENTRE(7), BOTTOM_RIGHT(8);

    private final int arrayPosition;

    Position(int arrayPosition)
    {
        this.arrayPosition = arrayPosition;
    }

    public int getArrayPosition()
    {
        return arrayPosition;
    }
}
