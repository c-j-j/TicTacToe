package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.Position;
import tictactoe.data.Seed;

public class BoardQueryTest
{
    @Test
    public void shouldReturnTrueWhenPositionIsOccupied() throws Exception
    {
        Position bottomCentre = Position.BOTTOM_CENTRE;
        Board board = new BoardBuilder().withMove(bottomCentre, Seed.COMPUTER).build();

        Assert.assertTrue(board.isPositionOccupied(bottomCentre));
    }

    @Test
    public void shouldReturnFalseWhenPositionIsOccupied() throws Exception
    {
        Board board = new BoardBuilder().withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER).build();

        Assert.assertFalse(board.isPositionOccupied(Position.TOP_CENTRE));
    }

    @Test
    public void shouldReturnUnoccupiedCornerPosition() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_RIGHT, Seed.COMPUTER)
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .build();

        Assert.assertThat(board.findEmptyCorner(), Matchers.is(Position.TOP_RIGHT));
    }

    @Test
    public void shouldReturnNullWhenNoCornersAreAvailable() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_RIGHT, Seed.COMPUTER)
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .build();

        Assert.assertThat(board.findEmptyCorner(), Matchers.nullValue());
    }

    @Test
    public void shouldReturnUnoccupiedSidePosition() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_CENTRE, Seed.COMPUTER)
                .withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER)
                .withMove(Position.MIDDLE_LEFT, Seed.COMPUTER)
                .build();


        Assert.assertThat(board.findEmptySide(), Matchers.is(Position.MIDDLE_RIGHT));
    }

    @Test
    public void shouldReturnNullWhenNoUnoccupiedSidePosition() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_CENTRE, Seed.COMPUTER)
                .withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER)
                .withMove(Position.MIDDLE_LEFT, Seed.COMPUTER)
                .withMove(Position.MIDDLE_RIGHT, Seed.COMPUTER)
                .build();

        Assert.assertThat(board.findEmptySide(), Matchers.nullValue());
    }

    @Test
    public void shouldReturnTrueIfSeedHasWon() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_CENTRE, Seed.COMPUTER)
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .build();

        Assert.assertThat(board.hasSeedWon(Seed.COMPUTER), Matchers.is(true));
    }

    @Test
    public void shouldReturnFalseIfSeedHasNotWon() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .build();

        Assert.assertThat(board.hasSeedWon(Seed.COMPUTER), Matchers.is(false));
    }
}