package tictactoe.integration;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import tictactoe.ComputerPlayer;
import tictactoe.Game;
import tictactoe.Token;
import tictactoe.builders.BoardFactory;
import tictactoe.data.GameProgress;
import tictactoe.data.GameState;
import tictactoe.data.Mark;

@Ignore
public class MinimaxIntegrationTest {

    public static final int NUMBER_OF_GAMES = 10;

    @Test
    public void computerVsComputerShouldResultInDrawAlways() {
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            GameState gameState = new Game(new ComputerPlayer(Mark.O), new ComputerPlayer(Mark.X)).play(BoardFactory.emptyBoard());
            Assert.assertThat(gameState, Matchers.is(GameState.DRAW));
        }
    }
}
