package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameProgress;
import tictactoe.data.Mark;
import tictactoe.data.Position;
import tictactoe.utils.PositionUtils;

import java.util.Scanner;

public class HumanPlayer implements Player
{
    private final Mark mark;
    private final GameRenderer gameRenderer;

    public HumanPlayer(Mark mark, GameRenderer gameRenderer)
    {
        this.mark = mark;
        this.gameRenderer = gameRenderer;
    }

    @Override
    public void play(Board board)
    {
        board.addMark(mark, gameRenderer.getPositionFromUser(board, mark));
    }
}
