package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class GameManagerTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};

    private GameManager gameManager;
    private WinnerChecker winnerChecker;

    @Before
    public void setUp() throws Exception
    {
        winnerChecker = mockery.mock(WinnerChecker.class);
        gameManager = new GameManager(winnerChecker);
    }

    @Test
    public void shouldReturnCentreWhenBoardIsEmpty() throws Exception
    {
        Board board = Board.newBoard();
        configureWinnerChecker(board, false);


        Position nextMove = gameManager.nextMove(board);
        Assert.assertThat(nextMove, Matchers.is(Position.CENTRE));
    }

    @Test
    public void shouldTryToWinGameWhenItCan() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .withMove(Position.TOP_CENTRE, Seed.COMPUTER)
                .build();

        configureWinnerChecker(board, true);

        Position nextMove = gameManager.nextMove(board);
        Assert.assertThat(nextMove, Matchers.is(Position.TOP_LEFT));
    }

    private void configureWinnerChecker(Board board, boolean result)
    {
        mockery.checking(new Expectations()
        {{
                oneOf(winnerChecker).canPlayerWin(board, Seed.COMPUTER);
                if (result)
                {
                    will(returnValue(new Result(Position.TOP_LEFT)));
                }
                else
                {
                    will(returnValue(Result.indeterminateResult()));
                }
            }});
    }
}
