package tictactoe;

public class Result
{
    private final Position nextMove;

    public Result(Position nextMove)
    {
        this.nextMove = nextMove;
    }

    public static Result indeterminateResult()
    {
        return new Result(null);
    }

    public boolean hasBeenDetermined()
    {
        return nextMove != null;

    }

    public Position getNextMove()
    {
        return nextMove;
    }
}
