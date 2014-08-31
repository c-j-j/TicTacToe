package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;

public class GameWinningTest {

    @Test
    public void shouldNotWinGameWhenNotPossible() {
        Board emptyBoard = new BoardBuilder().build();

        Result result = emptyBoard.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(false));
    }

    //X X e
    //e e e
    //e e e
    @Test
    public void shouldWinGameWhenTopLeftAndTopCentreOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_CENTRE, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.TOP_RIGHT));
    }

    //X e e
    //e e e
    //e e X
    @Test
    public void shouldWinGameWhenTopLeftAndBottomRightOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_RIGHT, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.CENTRE));
    }

    //X e e
    //e e e
    //X e e
    @Test
    public void shouldWinGameWhenTopLeftAndBottomLeftOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.MIDDLE_LEFT));
    }

    //e X e
    //e X e
    //e e e
    @Test
    public void shouldWinGameWhenTopMiddleAndCentreOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_CENTRE, Seed.COMPUTER)
                .withMove(Position.CENTRE, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.BOTTOM_CENTRE));
    }

    //e e X
    //e X e
    //e e e
    @Test
    public void shouldWinGameWhenTopRightAndCentreOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .withMove(Position.CENTRE, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.BOTTOM_LEFT));
    }

    //e e X
    //e e X
    //e e e
    @Test
    public void shouldWinGameWhenTopRightAndMiddleRightOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .withMove(Position.MIDDLE_RIGHT, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.BOTTOM_RIGHT));
    }

    //e e e
    //X e X
    //e e e
    @Test
    public void shouldWinGameWhenMiddleLeftAndMiddleRightOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.MIDDLE_LEFT, Seed.COMPUTER)
                .withMove(Position.MIDDLE_RIGHT, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.CENTRE));
    }

    //e e e
    //e e e
    //X X e
    @Test
    public void shouldWinGameWhenBottomLeftAndBottomCentreOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.BOTTOM_RIGHT));
    }

    //X e e
    //e e X
    //e e e
    @Test
    public void shouldNotWinWhenTopLeftAndMiddleRightOccupied() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.MIDDLE_RIGHT, Seed.COMPUTER)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(false));
    }

    //X O X
    //e e e
    //e e e
    @Test
    public void shouldNotWinGameIfOpponentIsInWay() {
        Board boardWithPotentialWin = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .withMove(Position.TOP_CENTRE, Seed.OPPONENT)
                .build();

        Result result = boardWithPotentialWin.canSeedWin(Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(false));
    }
}
