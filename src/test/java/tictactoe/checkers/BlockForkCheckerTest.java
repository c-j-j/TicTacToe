package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;
import tictactoe.builders.BoardBuilder;

public class BlockForkCheckerTest
{
    @Test
    public void shouldReturnIndeterminateResultWhenOpponentCannotFork() throws Exception
    {
        Board emptyBoard = new BoardBuilder().build();

        NextMoveResult nextMoveResult = new BlockForkChecker()
                .check(emptyBoard, Mark.O);

        Assert.assertThat(nextMoveResult.hasBeenDetermined(), Matchers.is(false));
    }

    //O E X
    //X E E
    //O E n
    @Test
    public void shouldBlockPositionWhenOpponentCanForkInOnePosition() throws Exception
    {
        Board boardWithSinglePotentialFork = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.O)
                .withMove(Position.BOTTOM_LEFT, Mark.O)
                .withMove(Position.MIDDLE_LEFT, Mark.X)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .build();

        NextMoveResult nextMoveResult = new BlockForkChecker()
                .check(boardWithSinglePotentialFork, Mark.X);

        Assert.assertThat(nextMoveResult.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(nextMoveResult.getNextMove(), Matchers.is(Position.BOTTOM_RIGHT));
    }

    //O n E
    //n X n
    //E n O
    @Test
    public void shouldForceOpponentToNonForkingPositionWhenOpponentCanForkInTwoPositions() throws Exception
    {
        Board boardWithSinglePotentialFork = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.O)
                .withMove(Position.BOTTOM_RIGHT, Mark.O)
                .withMove(Position.CENTRE, Mark.X)
                .build();

        NextMoveResult nextMoveResult = new BlockForkChecker()
                .check(boardWithSinglePotentialFork, Mark.X);

        Assert.assertThat(nextMoveResult.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(nextMoveResult.getNextMoves(), Matchers.containsInAnyOrder(Position.TOP_CENTRE, Position.MIDDLE_LEFT, Position.BOTTOM_CENTRE, Position.MIDDLE_RIGHT));
    }

    @Test
    public void shouldForceOpponentToNonForkingPositionWhenOpponentHasCentreAndCorner()
    {
        Board boardWithSinglePotentialFork = new BoardBuilder()
                .withMove(Position.CENTRE, Mark.O)
                .withMove(Position.BOTTOM_LEFT, Mark.O)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .build();

        NextMoveResult nextMoveResult = new BlockForkChecker()
                .check(boardWithSinglePotentialFork, Mark.X);

        Assert.assertThat(nextMoveResult.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(nextMoveResult.getNextMoves(), Matchers.containsInAnyOrder(Position.TOP_LEFT,Position.BOTTOM_RIGHT));


    }
}