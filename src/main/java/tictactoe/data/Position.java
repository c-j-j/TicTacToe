package tictactoe.data;

public enum Position
{
    TOP_LEFT(0), TOP_CENTRE(1), TOP_RIGHT(2),
    MIDDLE_LEFT(3), CENTRE(4), MIDDLE_RIGHT(5),
    BOTTOM_LEFT(6), BOTTOM_CENTRE(7), BOTTOM_RIGHT(8);
    private int integerRepresentation;

    Position(int integerRepresentation)
    {
        this.integerRepresentation = integerRepresentation;
    }

    public int getIntegerRepresentation()
    {
        return integerRepresentation;
    }

    public static Position getPosition(int integerRepresentationOfPosition)
    {
        for (Position position : Position.values())
        {
            if (position.integerRepresentation == integerRepresentationOfPosition)
            {
                return position;
            }
        }
        throw new IllegalArgumentException("Invalid integer given. Must be a number between 0-8");
    }
}
