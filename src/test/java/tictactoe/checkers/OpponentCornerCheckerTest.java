package tictactoe.checkers;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.Board;
import tictactoe.Position;
import tictactoe.Result;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;

public class OpponentCornerCheckerTest {
    @Test
    public void shouldReturnIndeterminateResultIfAllCornersAreOccupied() {

        Board boadWithOccupiedCorners = new BoardBuilder().withMove(Position.TOP_LEFT, Seed.OPPONENT)
                .withMove(Position.TOP_RIGHT, Seed.OPPONENT)
                .withMove(Position.BOTTOM_LEFT, Seed.OPPONENT)
                .withMove(Position.BOTTOM_RIGHT, Seed.OPPONENT)
                .build();

        Result result = new OpponentCornerChecker().check(boadWithOccupiedCorners, Seed.OPPONENT);

        Assert.assertThat(result.hasBeenDetermined(), Matchers.is(false));
    }

    //todo test for each opposite corner
}