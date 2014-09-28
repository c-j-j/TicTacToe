package tictactoe.integration;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import tictactoe.ComputerPlayer;
import tictactoe.Game;
import tictactoe.builders.BoardFactory;
import tictactoe.data.GameProgress;
import tictactoe.data.GameState;
@Ignore
public class MinimaxIntegrationTest {

    public static final int NUMBER_OF_GAMES = 100;

    @Test
    public void computerVsComputerShouldResultInDrawAlways() {
        for (int i = 0; i < NUMBER_OF_GAMES; i++) {
            GameProgress gameProgress = new Game(new ComputerPlayer(), new ComputerPlayer()).play(BoardFactory.emptyBoard());
            Assert.assertThat(gameProgress.getCurrentGameState(), Matchers.is(GameState.DRAW));
        }
    }
}
