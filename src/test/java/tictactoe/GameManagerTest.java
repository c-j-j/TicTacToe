package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.checkers.Checker;

public class GameManagerTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};

    private GameManager gameManager;
    private Checker winnerChecker;
    private Checker blockChecker;
    private Checker forkChecker;
    private Checker blockOpponentForkChecker;
    private Board board = new BoardBuilder().build();

    @Before
    public void setUp() throws Exception
    {
        winnerChecker = mockery.mock(Checker.class, "winnerChecker");
        blockChecker = mockery.mock(Checker.class, "blockChecker");
        forkChecker = mockery.mock(Checker.class, "forkChecker");
        blockOpponentForkChecker = mockery.mock(Checker.class, "blockOpponentForkChecker");
        gameManager = new GameManager(winnerChecker, blockChecker, forkChecker, blockOpponentForkChecker);
    }

    @Test
    public void shouldReturnCentreWhenBoardIsEmpty() throws Exception
    {
        configureChecker(winnerChecker, board, false);
        configureChecker(blockChecker, board, false);
        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, false);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(Position.CENTRE));
    }

    @Test
    public void shouldTryToWinGameWhenItCan() throws Exception
    {
        configureChecker(winnerChecker, board, true, Position.TOP_LEFT);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(Position.TOP_LEFT));
    }

    @Test
    public void shouldBlockOpponentIfCannotWin() throws Exception
    {
        Position simulatedNextMove = Position.BOTTOM_RIGHT;

        configureChecker(winnerChecker, board, false);
        configureChecker(blockChecker, board, true, simulatedNextMove);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(simulatedNextMove));
    }

    @Test
    public void shouldForkIfPossibleGivenNoBlockRequiredAndCannotWin() throws Exception
    {
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(winnerChecker, board, false);
        configureChecker(blockChecker, board, false);
        configureChecker(forkChecker, board, true, simulatedPosition);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(simulatedPosition));
    }

    @Test
    public void shouldBlockPotentialForkFromOpponentIfForkIsNotPossible() throws Exception
    {
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(winnerChecker, board, false);
        configureChecker(blockChecker, board, false);
        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, true, simulatedPosition);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(simulatedPosition));
    }

    private void configureChecker(Checker checker, Board board, boolean result)
    {
        configureChecker(checker, board, result, Position.CENTRE);
    }

    private void configureChecker(Checker checker, Board board, boolean result, Position position)
    {
        mockery.checking(new Expectations()
        {{
                oneOf(checker).check(board, Seed.COMPUTER);

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

}
