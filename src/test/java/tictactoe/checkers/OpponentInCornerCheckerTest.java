package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;
import tictactoe.builders.BoardBuilder;

public class OpponentInCornerCheckerTest
{
    @Test
    public void shouldReturnIndeterminateResultIfAllCornersAreOccupied()
    {

        Board boardWithOccupiedCorners = new BoardBuilder().withMove(Position.TOP_LEFT, Mark.O)
                .withMove(Position.TOP_RIGHT, Mark.O)
                .withMove(Position.BOTTOM_LEFT, Mark.O)
                .withMove(Position.BOTTOM_RIGHT, Mark.O)
                .build();

        NextMoveResult nextMoveResult = new OpponentInCornerChecker().check(boardWithOccupiedCorners, Mark.X);

        Assert.assertThat(nextMoveResult.hasBeenDetermined(), Matchers.is(false));
    }

    @Test
    public void shouldGoTopRightWhenOpponentInBottomLeft() throws Exception
    {
        testOppositeCornerGetsOccupied(Position.BOTTOM_LEFT, Position.TOP_RIGHT);
    }

    @Test
    public void shouldGoBottomRightWhenOpponentInTopLeft() throws Exception
    {
        testOppositeCornerGetsOccupied(Position.TOP_LEFT, Position.BOTTOM_RIGHT);
    }

    @Test
    public void shouldGoTopLeftWhenOpponentInBottomRight() throws Exception
    {
        testOppositeCornerGetsOccupied(Position.BOTTOM_RIGHT, Position.TOP_LEFT);
    }

    @Test
    public void shouldGoBottomLeftWhenOpponentInTopRight() throws Exception
    {
        testOppositeCornerGetsOccupied(Position.TOP_RIGHT, Position.BOTTOM_LEFT);
    }

    private void testOppositeCornerGetsOccupied(Position opponentPosition, Position expectedNextPositionFromPlayer)
    {
        Board boardWhereOpponentHasCorner = new BoardBuilder()
                .withMove(opponentPosition, Mark.O)
                .build();

        NextMoveResult nextMoveResult = new OpponentInCornerChecker().check(boardWhereOpponentHasCorner, Mark.X);

        Assert.assertThat(nextMoveResult.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(nextMoveResult.getNextMove(), Matchers.is(expectedNextPositionFromPlayer));
    }
}