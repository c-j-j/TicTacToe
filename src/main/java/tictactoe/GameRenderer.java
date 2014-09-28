package tictactoe;

import tictactoe.data.GameProgress;

public interface GameRenderer
{
    public void updateBoard();

    public void endResult(GameProgress result);

    boolean newGameQuery();
}
