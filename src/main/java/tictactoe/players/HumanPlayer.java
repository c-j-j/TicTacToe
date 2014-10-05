package tictactoe.players;

import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.render.GameRenderer;

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
        board.addMark(gameRenderer.getPositionFromUser(board, mark), mark);
    }
}
