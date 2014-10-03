package tictactoe.builders;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public class BoardFactoryTest
{
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowMoveToBeOverriden() throws Exception
    {
        Position newPosition = Position.BOTTOM_CENTRE;
        Board board = new BoardBuilder().withMove(newPosition, Mark.X).build();

        BoardFactory.addMove(board, newPosition, Mark.O);
    }

    @Test
    public void shouldBuildNewBoard() throws Exception
    {
        Board emptyBoard = BoardFactory.emptyBoard();

        for (Mark mark : emptyBoard.getMoves().values())
        {
            Assert.assertThat(mark, Matchers.is(Mark.EMPTY));
        }
    }
}
