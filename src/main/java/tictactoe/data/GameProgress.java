package tictactoe.data;

import com.google.gson.Gson;

public class GameProgress
{
    private final GameState currentGameState;
    private final Board board;

    public GameProgress(GameState currentGameState, Board board)
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

    public static GameProgress inflateFromJson(String json)
    {
        return new Gson().fromJson(json, GameProgress.class);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameProgress that = (GameProgress) o;

        return !(board != null ? !board.equals(that.board) : that.board != null) && currentGameState == that.currentGameState;

    }

    @Override
    public int hashCode()
    {
        int result = currentGameState != null ? currentGameState.hashCode() : 0;
        result = 31 * result + (board != null ? board.hashCode() : 0);
        return result;
    }

    public String toJson()
    {
        return new Gson().toJson(this);
    }


}
