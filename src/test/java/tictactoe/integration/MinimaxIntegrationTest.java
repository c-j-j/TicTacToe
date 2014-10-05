package tictactoe.integration;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.players.ComputerPlayer;
import tictactoe.Game;
import tictactoe.builders.BoardBuilder;
import tictactoe.builders.BoardFactory;
import tictactoe.data.*;
import tictactoe.render.DummyGameRenderer;
import tictactoe.render.GameRenderer;

public class MinimaxIntegrationTest
{
    @Test
    public void computerVsComputerShouldResultInDrawWhenBoardIsInitiallyEmpty()
    {
        assertGameEndsInDraw(BoardFactory.emptyBoard());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenTopLeftIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.TOP_LEFT, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenTopCentreIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.TOP_CENTRE, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenTopRightIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.TOP_RIGHT, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenMiddleLeftIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.MIDDLE_LEFT, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenCentreIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.CENTRE, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenMiddleRightIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.MIDDLE_RIGHT, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenBottomLeftIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.BOTTOM_LEFT, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenBottomCentreIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.BOTTOM_CENTRE, Mark.X).build());
    }

    @Test
    public void computerVsComputerShouldResultInDrawWhenBottomRightIsFirstMarked()
    {
        assertGameEndsInDraw(new BoardBuilder().withMove(Position.BOTTOM_RIGHT, Mark.X).build());
    }


    private void assertGameEndsInDraw(Board board)
    {
        GameOutcome gameOutcome = new Game(new DummyGameRenderer()).play(board, new ComputerPlayer(Mark.O), new ComputerPlayer(Mark.X));
        Assert.assertThat(gameOutcome, Matchers.is(GameOutcome.DRAW));
    }
}
