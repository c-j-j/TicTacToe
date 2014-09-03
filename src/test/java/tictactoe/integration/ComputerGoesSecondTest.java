package tictactoe.integration;

import org.junit.Ignore;
import org.junit.Test;
import tictactoe.data.Position;

public class ComputerGoesSecondTest extends IntegrationTestBase
{
    @Test
    @Ignore //the game is not idempotent. However, although this makes integration tests impossible to automate, it does result in a more nondeterministic tictactoe opponent
    public void shouldBlockForkByAggressivelyTryingToWinWhichWillLeadToDraw() throws Exception
    {
        playerGoes(Position.TOP_LEFT);
        computerShouldGo(Position.CENTRE);
        playerGoes(Position.BOTTOM_RIGHT);
        computerShouldGo(Position.TOP_CENTRE);
        playerGoes(Position.BOTTOM_CENTRE);
        computerShouldGo(Position.BOTTOM_LEFT);
        playerGoes(Position.TOP_RIGHT);
        computerShouldGo(Position.MIDDLE_RIGHT);
        playerGoes(Position.MIDDLE_LEFT);

        shouldBeADraw();
    }

}
