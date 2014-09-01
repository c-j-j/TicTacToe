package tictactoe.integration;

import org.junit.Ignore;
import org.junit.Test;
import tictactoe.data.Position;

public class ComputerGoesFirstTest extends IntegrationTestBase
{
    @Test
    @Ignore
    public void shouldWinWhenComputerGoesFirstAndOpponentPlaysCorner() throws Exception
    {
        computerShouldGo(Position.CENTRE);
        playerGoes(Position.BOTTOM_LEFT);
        computerShouldGo(Position.TOP_RIGHT);
        playerGoes(Position.BOTTOM_CENTRE);
        computerShouldGo(Position.BOTTOM_RIGHT);
        playerGoes(Position.MIDDLE_RIGHT);
        computerShouldGo(Position.TOP_LEFT);

        computerHasWon();
    }

    @Test
    @Ignore
    public void shouldWinWhenComputerGoesFirstAndOpponentPlaysSide() throws Exception
    {
        computerShouldGo(Position.CENTRE);
        playerGoes(Position.TOP_CENTRE);
        computerShouldGo(Position.TOP_RIGHT);
        playerGoes(Position.BOTTOM_LEFT);
        computerShouldGo(Position.MIDDLE_RIGHT);
        playerGoes(Position.BOTTOM_RIGHT);
        computerShouldGo(Position.MIDDLE_LEFT);

        computerHasWon();
    }
}
