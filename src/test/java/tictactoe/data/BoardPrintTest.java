package tictactoe.data;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;

public class BoardPrintTest
{
    @Test
    public void shouldPrintBoardAccordingly() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_CENTRE, Mark.O)
                .withMove(Position.TOP_RIGHT, Mark.O)
                .withMove(Position.MIDDLE_LEFT, Mark.O)
                .withMove(Position.CENTRE, Mark.O)
                .withMove(Position.MIDDLE_RIGHT, Mark.X)
                .withMove(Position.BOTTOM_LEFT, Mark.X)
                .withMove(Position.BOTTOM_CENTRE, Mark.X)
                .withMove(Position.BOTTOM_RIGHT, Mark.O)
                .build();

        Assert.assertThat(board.toString(), Matchers.containsString(String.format(Board.OUTPUT_LINE_FORMAT, Mark.X, Mark.O, Mark.O)));
        Assert.assertThat(board.toString(), Matchers.containsString(String.format(Board.OUTPUT_LINE_FORMAT, Mark.O, Mark.O, Mark.X)));
        Assert.assertThat(board.toString(), Matchers.containsString(String.format(Board.OUTPUT_LINE_FORMAT, Mark.X, Mark.X, Mark.O)));
    }
}