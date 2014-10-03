package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.builders.BoardFactory;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;

import static org.junit.Assert.*;

public class ComputerPlayerTest {
    @Test
    public void computerShouldGoToCentreFirst() throws Exception {
        ComputerPlayer computerPlayer = new ComputerPlayer(Mark.X);

        Board board = BoardFactory.emptyBoard();
        computerPlayer.play(board);

        Assert.assertThat(board.isPositionOccupied(Position.CENTRE), Matchers.is(true));
    }

    @Test
    public void shouldGoCentreWhenOpponentIsInCorner() throws Exception {
        ComputerPlayer computerPlayer = new ComputerPlayer(Mark.X);
        Board board = new BoardBuilder().withMove(Position.TOP_LEFT, Mark.O).build();
        computerPlayer.play(board);

        Assert.assertThat(board.isPositionOccupied(Position.CENTRE), Matchers.is(true));
    }
}