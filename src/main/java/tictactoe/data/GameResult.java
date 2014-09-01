package tictactoe.data;

public class GameResult
{
    private final GameState currentGameState;
    private final Board board;

    public GameResult(GameState currentGameState, Board board)
    {
        this.currentGameState = currentGameState;
        this.board = board;
    }

    public GameState getCurrentGameState()
    {
        return currentGameState;
    }

    public Board getBoard()
    {
        return board;
    }
}
