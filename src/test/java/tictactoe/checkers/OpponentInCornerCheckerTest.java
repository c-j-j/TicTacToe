package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.Board;
import tictactoe.Position;
import tictactoe.Result;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;

public class OpponentInCornerCheckerTest
{
    @Test
    public void shouldReturnIndeterminateResultIfAllCornersAreOccupied()
    {

        Board boardWithOccupiedCorners = new BoardBuilder().withMove(Position.TOP_LEFT, Seed.OPPONENT)
                .withMove(Position.TOP_RIGHT, Seed.OPPONENT)
                .withMove(Position.BOTTOM_LEFT, Seed.OPPONENT)
                .withMove(Position.BOTTOM_RIGHT, Seed.OPPONENT)
                .build();

        Result result = new OpponentInCornerChecker().check(boardWithOccupiedCorners, Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(false));
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
                .withMove(opponentPosition, Seed.OPPONENT)
                .build();

        Result result = new OpponentInCornerChecker().check(boardWhereOpponentHasCorner, Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(expectedNextPositionFromPlayer));
    }
}