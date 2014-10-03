package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;
import tictactoe.builders.BoardBuilder;

public class ForkCheckerTest {

    @Test
    public void shouldNotReturnResultWhenNoForkIsPossible() {
        Board emptyBoard = new BoardBuilder()
                .build();

        NextMoveResult forkNextMoveResult = new ForkChecker().check(emptyBoard, Mark.X);

        Assert.assertThat(forkNextMoveResult.hasBeenDetermined(), Matchers.is(false));
    }

    //X E n
    //O n E
    //X E n
    @Test
    public void shouldReturnResultWhenForkingOpportunityIsAvailable() {

        Board emptyBoard = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.BOTTOM_LEFT, Mark.X)
                .withMove(Position.MIDDLE_LEFT, Mark.O)
                .build();


        NextMoveResult forkNextMoveResult = new ForkChecker().check(emptyBoard, Mark.X);

        Assert.assertThat(forkNextMoveResult.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(forkNextMoveResult.getNextMoves(), Matchers.containsInAnyOrder(Position.CENTRE, Position.TOP_RIGHT, Position.BOTTOM_RIGHT));
    }

}