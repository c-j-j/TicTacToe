package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.Board;
import tictactoe.NextMoveResult;
import tictactoe.Position;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;

public class ForkCheckerTest {

    @Test
    public void shouldNotReturnResultWhenNoForkIsPossible() {
        Board emptyBoard = new BoardBuilder()
                .build();

        NextMoveResult forkNextMoveResult = new ForkChecker().check(emptyBoard, Seed.COMPUTER);

        Assert.assertThat(forkNextMoveResult.hasBeenDetermined(), Matchers.is(false));
    }

    //X E n
    //O n E
    //X E n
    @Test
    public void shouldReturnResultWhenForkingOpportunityIsAvailable() {

        Board emptyBoard = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .withMove(Position.MIDDLE_LEFT, Seed.OPPONENT)
                .build();


        NextMoveResult forkNextMoveResult = new ForkChecker().check(emptyBoard, Seed.COMPUTER);

        Assert.assertThat(forkNextMoveResult.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(forkNextMoveResult.getNextMoves(), Matchers.containsInAnyOrder(Position.CENTRE, Position.TOP_RIGHT, Position.BOTTOM_RIGHT));
    }

}