package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.Board;
import tictactoe.Position;
import tictactoe.Result;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;

public class ForkCheckerTest {

    @Test
    public void shouldNotReturnResultWhenNoForkIsPossible() {
        Board emptyBoard = new BoardBuilder()
                .build();

        Result forkResult = new ForkChecker().check(emptyBoard, Seed.COMPUTER);

        Assert.assertThat(forkResult.hasBeenDetermined(), Matchers.is(false));
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


        Result forkResult = new ForkChecker().check(emptyBoard, Seed.COMPUTER);

        Assert.assertThat(forkResult.hasBeenDetermined(), Matchers.is(true));
        Assert.assertThat(forkResult.getNextMoves(), Matchers.containsInAnyOrder(Position.CENTRE, Position.TOP_RIGHT, Position.BOTTOM_RIGHT));
    }

}