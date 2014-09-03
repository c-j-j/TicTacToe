package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.Position;
import tictactoe.data.Seed;

public class BoardJsonTest
{
    @Test
    public void shouldInflateBoardIntoJson() throws Exception
    {
        Board board = new BoardBuilder().withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER).build();

        String json = board.toJson();

        Board inflatedBoard = Board.inflateFromJson(json);

        Assert.assertThat(board.getMoves(), Matchers.is(inflatedBoard.getMoves()));
    }
}
