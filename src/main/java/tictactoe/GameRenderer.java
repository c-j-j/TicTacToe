package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameProgress;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public interface GameRenderer
{
    public void draw(Board board);

    public void endResult(GameProgress result);

    boolean newGameQuery();

    Position getPositionFromUser(Board board, Mark mark);
}
