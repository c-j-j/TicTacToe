package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.Board;
import tictactoe.Position;
import tictactoe.Result;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;

public class BlockForkCheckerTest
{
    @Test
    public void shouldReturnIndeterminateResultWhenOpponentCannotFork() throws Exception
    {
        Board emptyBoard = new BoardBuilder().build();

        Result result = new BlockForkChecker()
                .check(emptyBoard, Seed.OPPONENT);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(false));
    }

    //O E X
    //X E E
    //O E n
    @Test
    public void shouldBlockPositionWhenOpponentCanForkInOnePosition() throws Exception
    {
        Board boardWithSinglePotentialFork = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.OPPONENT)
                .withMove(Position.BOTTOM_LEFT, Seed.OPPONENT)
                .withMove(Position.MIDDLE_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .build();

        Result result = new BlockForkChecker()
                .check(boardWithSinglePotentialFork, Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMove(), Matchers.is(Position.BOTTOM_RIGHT));
    }

    //O n E
    //n X n
    //E n O
    @Test
    public void shouldForceOpponentToNonForkingPositionWhenOpponentCanForkInTwoPositions() throws Exception
    {
        Board boardWithSinglePotentialFork = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.OPPONENT)
                .withMove(Position.BOTTOM_RIGHT, Seed.OPPONENT)
                .withMove(Position.CENTRE, Seed.COMPUTER)
                .build();

        Result result = new BlockForkChecker()
                .check(boardWithSinglePotentialFork, Seed.COMPUTER);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(result.getNextMoves(), Matchers.containsInAnyOrder(Position.TOP_CENTRE, Position.MIDDLE_LEFT, Position.BOTTOM_CENTRE, Position.MIDDLE_RIGHT));
    }
}