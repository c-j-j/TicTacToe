package tictactoe.render;

import tictactoe.data.Board;
import tictactoe.data.GameOutcome;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public interface GameRenderer
{
    public void draw(Board board);

    public void displayResult(GameOutcome winner);

    Position getPositionFromUser(Board board, Mark mark);
}
