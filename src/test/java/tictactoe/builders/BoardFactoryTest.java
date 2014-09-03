package tictactoe.builders;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.data.Board;
import tictactoe.data.Position;
import tictactoe.data.Seed;

public class BoardFactoryTest
{
    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAllowMoveToBeOverriden() throws Exception
    {
        Position newPosition = Position.BOTTOM_CENTRE;
        Board board = new BoardBuilder().withMove(newPosition, Seed.COMPUTER).build();

        BoardFactory.addMove(board, newPosition, Seed.OPPONENT);
    }

    @Test
    public void shouldBuildNewBoard() throws Exception
    {
        Board emptyBoard = BoardFactory.emptyBoard();

        for (Seed seed : emptyBoard.getMoves().values())
        {
            Assert.assertThat(seed, Matchers.is(Seed.EMPTY));
        }
    }
}
