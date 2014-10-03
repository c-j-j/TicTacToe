package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public class BoardQueryTest {
    @Test
    public void shouldReturnTrueWhenPositionIsOccupied() throws Exception {
        Position bottomCentre = Position.BOTTOM_CENTRE;
        Board board = new BoardBuilder().withMove(bottomCentre, Mark.X).build();

        Assert.assertTrue(board.isPositionOccupied(bottomCentre));
    }

    @Test
    public void shouldReturnFalseWhenPositionIsOccupied() throws Exception {
        Board board = new BoardBuilder().withMove(Position.BOTTOM_CENTRE, Mark.X).build();

        Assert.assertFalse(board.isPositionOccupied(Position.TOP_CENTRE));
    }

    @Test
    public void shouldReturnUnoccupiedCornerPosition() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.BOTTOM_LEFT, Mark.X)
                .withMove(Position.BOTTOM_RIGHT, Mark.X)
                .withMove(Position.TOP_LEFT, Mark.X)
                .build();

        Assert.assertThat(board.findEmptyCorner(), Matchers.is(Position.TOP_RIGHT));
    }

    @Test
    public void shouldReturnNullWhenNoCornersAreAvailable() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.BOTTOM_LEFT, Mark.X)
                .withMove(Position.BOTTOM_RIGHT, Mark.X)
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .build();

        Assert.assertThat(board.findEmptyCorner(), Matchers.nullValue());
    }

    @Test
    public void shouldReturnUnoccupiedSidePosition() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_CENTRE, Mark.X)
                .withMove(Position.BOTTOM_CENTRE, Mark.X)
                .withMove(Position.MIDDLE_LEFT, Mark.X)
                .build();


        Assert.assertThat(board.findEmptySide(), Matchers.is(Position.MIDDLE_RIGHT));
    }

    @Test
    public void shouldReturnNullWhenNoUnoccupiedSidePosition() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_CENTRE, Mark.X)
                .withMove(Position.BOTTOM_CENTRE, Mark.X)
                .withMove(Position.MIDDLE_LEFT, Mark.X)
                .withMove(Position.MIDDLE_RIGHT, Mark.X)
                .build();

        Assert.assertThat(board.findEmptySide(), Matchers.nullValue());
    }

    @Test
    public void shouldReturnTrueIfSeedHasWon() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_CENTRE, Mark.X)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .build();

        Assert.assertThat(board.hasSeedWon(Mark.X), Matchers.is(true));
    }

    @Test
    public void shouldReturnFalseIfSeedHasNotWon() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .build();

        Assert.assertThat(board.hasSeedWon(Mark.X), Matchers.is(false));
    }

    @Test
    public void isGameOverShouldBeTrueIfXHasWon() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .withMove(Position.TOP_CENTRE, Mark.X)
                .build();

        Assert.assertThat(board.isGameOver(), Matchers.is(true));
    }

    @Test
    public void isGameOverShouldBeTrueIfOHasWon() throws Exception {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.O)
                .withMove(Position.TOP_RIGHT, Mark.O)
                .withMove(Position.TOP_CENTRE, Mark.O)
                .build();

        Assert.assertThat(board.isGameOver(), Matchers.is(true));
    }

    @Test
    public void isGameOverShouldBeTrueIfItIsADraw() throws Exception {
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

        Assert.assertThat(board.isGameOver(), Matchers.is(true));
    }

    @Test
    public void isGameOverShouldBeFalseIfGameIsInProgress() throws Exception {
        Assert.assertThat(new BoardBuilder().build().isGameOver(), Matchers.is(false));

    }
}