package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;
import tictactoe.players.ComputerPlayer;

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

    //MinimaxIntegrationTest thoroughly tests the behaviour of ComputerPlayer by having two ComputerPlayers face each other in various scenarios.
}