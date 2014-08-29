package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.checkers.BlockChecker;
import tictactoe.checkers.WinnerChecker;

public class GameManagerTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};

    private GameManager gameManager;
    private WinnerChecker winnerChecker;
    private BlockChecker blockChecker;

    @Before
    public void setUp() throws Exception
    {
        winnerChecker = mockery.mock(WinnerChecker.class);
        blockChecker = mockery.mock(BlockChecker.class);
        gameManager = new GameManager(winnerChecker, blockChecker);
    }

    @Test
    public void shouldReturnCentreWhenBoardIsEmpty() throws Exception
    {
        Board board = Board.newBoard();
        configureWinnerChecker(board, false);
        configureBlockChecker(board, false);

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

    @Test
    public void shouldBlockOpponentIfCannotWin() throws Exception
    {
        Board board = new BoardBuilder().build();

        configureWinnerChecker(board, false);
        Position simulatedNextMove = Position.BOTTOM_RIGHT;
        configureBlockChecker(board, true, simulatedNextMove);

        Position nextMove = gameManager.nextMove(board);
        Assert.assertThat(nextMove, Matchers.is(simulatedNextMove));
    }

    private void configureBlockChecker(Board board, boolean result)
    {
        configureBlockChecker(board, result, Position.BOTTOM_CENTRE);
    }

    private void configureBlockChecker(Board board, boolean result, final Position position)
    {
        mockery.checking(new Expectations()
        {{
                oneOf(blockChecker).mustBlock(board, Seed.COMPUTER);
                if (result)
                {
                    will(returnValue(new Result(position)));
                }
                else
                {
                    will(returnValue(Result.indeterminateResult()));
                }
            }});
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
