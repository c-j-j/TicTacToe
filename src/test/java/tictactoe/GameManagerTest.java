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
    private Checker cornerChecker;
    private Board emptyBoard = new BoardBuilder().build();

    @Before
    public void setUp() throws Exception
    {
        winnerChecker = mockery.mock(Checker.class, "winnerChecker");
        blockChecker = mockery.mock(Checker.class, "blockChecker");
        forkChecker = mockery.mock(Checker.class, "forkChecker");
        cornerChecker = mockery.mock(Checker.class, "cornerChecker");
        blockOpponentForkChecker = mockery.mock(Checker.class, "blockOpponentForkChecker");
        gameManager = new GameManager(winnerChecker, blockChecker, forkChecker, blockOpponentForkChecker, cornerChecker);
    }

    @Test
    public void shouldTryToWinGameWhenItCan() throws Exception
    {
        configureChecker(winnerChecker, emptyBoard, true, Position.TOP_LEFT);

        Assert.assertThat(gameManager.nextMove(emptyBoard), Matchers.is(Position.TOP_LEFT));
    }

    @Test
    public void shouldBlockOpponentIfCannotWin() throws Exception
    {
        Position simulatedNextMove = Position.BOTTOM_RIGHT;

        configureChecker(winnerChecker, emptyBoard, false);
        configureChecker(blockChecker, emptyBoard, true, simulatedNextMove);

        Assert.assertThat(gameManager.nextMove(emptyBoard), Matchers.is(simulatedNextMove));
    }

    @Test
    public void shouldForkIfPossibleGivenNoBlockRequiredAndCannotWin() throws Exception
    {
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(winnerChecker, emptyBoard, false);
        configureChecker(blockChecker, emptyBoard, false);
        configureChecker(forkChecker, emptyBoard, true, simulatedPosition);

        Assert.assertThat(gameManager.nextMove(emptyBoard), Matchers.is(simulatedPosition));
    }

    @Test
    public void shouldBlockPotentialForkFromOpponentIfForkIsNotPossible() throws Exception
    {
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(winnerChecker, emptyBoard, false);
        configureChecker(blockChecker, emptyBoard, false);
        configureChecker(forkChecker, emptyBoard, false);
        configureChecker(blockOpponentForkChecker, emptyBoard, true, simulatedPosition);

        Assert.assertThat(gameManager.nextMove(emptyBoard), Matchers.is(simulatedPosition));
    }

    @Test
    public void shouldReturnCentreWhenCheckersYieldNoResultsAndCentreIsntOccupied() throws Exception
    {
        configureChecker(winnerChecker, emptyBoard, false);
        configureChecker(blockChecker, emptyBoard, false);
        configureChecker(forkChecker, emptyBoard, false);
        configureChecker(blockOpponentForkChecker, emptyBoard, false);

        Assert.assertThat(gameManager.nextMove(emptyBoard), Matchers.is(Position.CENTRE));
    }

    @Test
    public void shouldInvokeCornerCheckerToOpponentIfOtherCheckersYeildNoResultsAndCentreIsOccupied() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.CENTRE, Seed.OPPONENT)
                .build();
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(winnerChecker, board, false);
        configureChecker(blockChecker, board, false);
        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, false);
        configureChecker(cornerChecker, board, true, simulatedPosition);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(simulatedPosition));
    }

    @Test
    public void shouldFindEmptyCornerGivenABoardWithOccupiedCentreAndAllOtherChecksYieldNoResults() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.CENTRE, Seed.OPPONENT)
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_RIGHT, Seed.COMPUTER)
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .build();

        configureChecker(winnerChecker, board, false);
        configureChecker(blockChecker, board, false);
        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, false);
        configureChecker(cornerChecker, board, false);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(Position.TOP_RIGHT));
    }

    @Test
    public void shouldPlayAnEmptySideGivenAllOtherChecksFailed() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.CENTRE, Seed.OPPONENT)
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_RIGHT, Seed.COMPUTER)
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_RIGHT, Seed.COMPUTER)
                .withMove(Position.TOP_CENTRE, Seed.COMPUTER)
                .withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER)
                .withMove(Position.MIDDLE_LEFT, Seed.COMPUTER)
                .build();

        configureChecker(winnerChecker, board, false);
        configureChecker(blockChecker, board, false);
        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, false);
        configureChecker(cornerChecker, board, false);

        Assert.assertThat(gameManager.nextMove(board), Matchers.is(Position.MIDDLE_RIGHT));
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
