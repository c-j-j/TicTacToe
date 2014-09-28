package tictactoe;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import tictactoe.data.Board;
import tictactoe.data.GameProgress;

public class ComputerPlayer implements Player
{
    private Token token;

    public ComputerPlayer(Token token)
    {
        this.token = token;
    }

    @Override
    public GameProgress play(Board board)
    {
        throw new NotImplementedException();
    }
}
