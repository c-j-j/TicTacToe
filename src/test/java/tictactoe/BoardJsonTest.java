package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.Position;
import tictactoe.data.Mark;

public class BoardJsonTest
{
    @Test
    public void shouldInflateBoardIntoJson() throws Exception
    {
        Board board = new BoardBuilder().withMove(Position.BOTTOM_CENTRE, Mark.X).build();

        String json = board.toJson();

        Board inflatedBoard = Board.inflateFromJson(json);

        Assert.assertThat(board, Matchers.is(inflatedBoard));
        Assert.assertThat(board.hashCode(), Matchers.is(inflatedBoard.hashCode()));
    }
}
