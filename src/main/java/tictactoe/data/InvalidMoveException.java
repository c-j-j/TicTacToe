package tictactoe.data;

public class InvalidMoveException extends RuntimeException
{
    public InvalidMoveException(String message) {
        super(message);
    }
}
