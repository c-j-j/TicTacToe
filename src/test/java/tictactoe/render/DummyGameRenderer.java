package tictactoe.render;

import tictactoe.data.Board;
import tictactoe.data.GameOutcome;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public class DummyGameRenderer implements GameRenderer
{
    @Override
    public void draw(Board board)
    {
        //leave blank
    }

    @Override
    public void displayResult(GameOutcome winner)
    {
        //leave blank
    }

    @Override
    public Position getPositionFromUser(Board board, Mark mark)
    {
        throw new IllegalAccessError("Use real renderer if user input required");
    }
}
