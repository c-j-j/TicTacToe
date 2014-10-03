package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.checkers.BlockForkChecker;
import tictactoe.checkers.Checker;
import tictactoe.checkers.ForkChecker;
import tictactoe.checkers.OpponentInCornerChecker;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;

public class NextPositionHandlerTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
            setThreadingPolicy(new Synchroniser());
        }};

    private NextPositionHandler nextPositionHandler;
    private ForkChecker forkChecker;
    private BlockForkChecker blockOpponentForkChecker;
    private OpponentInCornerChecker cornerChecker;
    private Board emptyBoard = new BoardBuilder().build();

    @Before
    public void setUp() throws Exception
    {
        forkChecker = mockery.mock(ForkChecker.class, "forkChecker");
        cornerChecker = mockery.mock(OpponentInCornerChecker.class, "cornerChecker");
        blockOpponentForkChecker = mockery.mock(BlockForkChecker.class, "blockOpponentForkChecker");
        nextPositionHandler = new NextPositionHandler(forkChecker, blockOpponentForkChecker, cornerChecker);
    }

    @Test
    public void shouldTryToWinGameWhenItCan() throws Exception
    {
        Board potentialWinningBoard = new BoardBuilder()
                .withMove(Position.BOTTOM_LEFT, Mark.X)
                .withMove(Position.MIDDLE_LEFT, Mark.X)
                .build();


        Assert.assertThat(nextPositionHandler.nextMove(potentialWinningBoard), Matchers.is(Position.TOP_LEFT));
    }

    @Test
    public void shouldBlockOpponentIfCannotWin() throws Exception
    {
        Board potentialBlockingBoard = new BoardBuilder()
                .withMove(Position.BOTTOM_LEFT, Mark.X)
                .withMove(Position.MIDDLE_LEFT, Mark.X)
                .build();

        Assert.assertThat(nextPositionHandler.nextMove(potentialBlockingBoard), Matchers.is(Position.TOP_LEFT));
    }

    @Test
    public void shouldForkIfPossibleGivenNoBlockRequiredAndCannotWin() throws Exception
    {
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(forkChecker, emptyBoard, true, simulatedPosition);

        Assert.assertThat(nextPositionHandler.nextMove(emptyBoard), Matchers.is(simulatedPosition));
    }

    @Test
    public void shouldBlockPotentialForkFromOpponentIfForkIsNotPossible() throws Exception
    {
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(forkChecker, emptyBoard, false);
        configureChecker(blockOpponentForkChecker, emptyBoard, true, simulatedPosition);

        Assert.assertThat(nextPositionHandler.nextMove(emptyBoard), Matchers.is(simulatedPosition));
    }

    @Test
    public void shouldReturnCentreWhenCheckersYieldNoResultsAndCentreIsntOccupied() throws Exception
    {
        configureChecker(forkChecker, emptyBoard, false);
        configureChecker(blockOpponentForkChecker, emptyBoard, false);

        Assert.assertThat(nextPositionHandler.nextMove(emptyBoard), Matchers.is(Position.CENTRE));
    }

    @Test
    public void shouldInvokeCornerCheckerToOpponentIfOtherCheckersYeildNoResultsAndCentreIsOccupied() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.CENTRE, Mark.O)
                .build();
        Position simulatedPosition = Position.BOTTOM_CENTRE;

        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, false);
        configureChecker(cornerChecker, board, true, simulatedPosition);

        Assert.assertThat(nextPositionHandler.nextMove(board), Matchers.is(simulatedPosition));
    }

    @Test
    public void shouldFindEmptyCornerGivenABoardWithOccupiedCentreAndAllOtherChecksYieldNoResults() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.CENTRE, Mark.O)
                .build();

        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, false);
        configureChecker(cornerChecker, board, false);

        Assert.assertThat(nextPositionHandler.nextMove(board), Matchers.is(Position.TOP_RIGHT));
    }

    //X O X
    //E X O
    //O X X
    @Test
    public void shouldPlayAnEmptySideGivenAllOtherChecksFailed() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.CENTRE, Mark.X)
                .withMove(Position.BOTTOM_LEFT, Mark.O)
                .withMove(Position.BOTTOM_RIGHT, Mark.X)
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .withMove(Position.TOP_CENTRE, Mark.O)
                .withMove(Position.BOTTOM_CENTRE, Mark.X)
                .withMove(Position.MIDDLE_RIGHT, Mark.O)
                .build();

        configureChecker(forkChecker, board, false);
        configureChecker(blockOpponentForkChecker, board, false);
        configureChecker(cornerChecker, board, false);

        Assert.assertThat(nextPositionHandler.nextMove(board), Matchers.is(Position.MIDDLE_LEFT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRunTimeExceptionWhenProvidedFullBoard() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.CENTRE, Mark.X)
                .withMove(Position.BOTTOM_LEFT, Mark.O)
                .withMove(Position.BOTTOM_RIGHT, Mark.X)
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_RIGHT, Mark.X)
                .withMove(Position.TOP_CENTRE, Mark.O)
                .withMove(Position.BOTTOM_CENTRE, Mark.X)
                .withMove(Position.MIDDLE_RIGHT, Mark.O)
                .withMove(Position.MIDDLE_LEFT, Mark.O)
                .build();

        nextPositionHandler.nextMove(board);
    }

    private void configureChecker(Checker checker, Board board, boolean result)
    {
        configureChecker(checker, board, result, Position.CENTRE);
    }

    private void configureChecker(final Checker checker, final Board board, final boolean result, final Position position)
    {
        mockery.checking(new Expectations()
        {{
                oneOf(checker).check(board, Mark.X);

                if (result)
                {
                    will(returnValue(new NextMoveResult(position)));
                }
                else
                {
                    will(returnValue(NextMoveResult.indeterminateResult()));
                }
            }});
    }

}
