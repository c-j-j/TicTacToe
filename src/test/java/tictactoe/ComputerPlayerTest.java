package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.builders.BoardFactory;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public class ComputerPlayerTest
{
    @Test
    public void shouldGoCentreWhenOpponentIsInCorner() throws Exception
    {
        ComputerPlayer computerPlayer = new ComputerPlayer(Mark.X);
        Board board = new BoardBuilder().withMove(Position.TOP_LEFT, Mark.O).build();
        computerPlayer.play(board);

        Assert.assertThat(board.isPositionOccupied(Position.CENTRE), Matchers.is(true));
    }

    @Test
    public void computerWillWinWhenItHasTwoInARow() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_CENTRE, Mark.X)
                .build();

        new ComputerPlayer(Mark.X).play(board);

        Assert.assertThat(board.toString(),board.getMark(Position.TOP_RIGHT), Matchers.is(Mark.X));
    }
}