package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.GameResult;
import tictactoe.data.GameState;
import tictactoe.data.Position;
import tictactoe.data.Seed;

public class GameManagerTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
    private GameStateManager gameStateManager;
    private NextPositionHandler nextPositionHandler;
    private GameManager gameManager;
    private Board board;

    @Before
    public void setUp() throws Exception
    {
        gameStateManager = mockery.mock(GameStateManager.class);
        nextPositionHandler = mockery.mock(NextPositionHandler.class);
        gameManager = new GameManager(gameStateManager, nextPositionHandler);
        board = new BoardBuilder().build();
    }

    @Test
    public void shouldReturnDrawIfGameStateManagerReturnsDraw() throws Exception
    {
        testTerminalGameStatus(GameState.DRAW);
    }

    @Test
    public void shouldReturnLossIfGameStateManagerReturnsLoss() throws Exception
    {
        testTerminalGameStatus(GameState.COMPUTER_LOSES);
    }

    @Test
    public void shouldReturnWinIfGameStateManagerReturnsWin() throws Exception
    {
        testTerminalGameStatus(GameState.COMPUTER_WINS);
    }


    @Test
    public void shouldCalculateNextMoveIfGameIsInProgress() throws Exception
    {
        configureGameStateManager(GameState.IN_PROGRESS, 2);
        final Position nextPosition = Position.CENTRE;
        configureNextPositionHandler(nextPosition);

        GameResult gameResult = gameManager.play(board);
        Assert.assertThat(gameResult.getCurrentGameState(), Matchers.is(GameState.IN_PROGRESS));
        Assert.assertThat(gameResult.getBoard().getSeed(nextPosition), Matchers.is(Seed.COMPUTER));
    }

    private void configureNextPositionHandler(final Position nextPosition)
    {
        mockery.checking(new Expectations()
        {{
                oneOf(nextPositionHandler).nextMove(board);
                will(returnValue(nextPosition));
            }});
    }

    private void testTerminalGameStatus(GameState gameState)
    {
        configureGameStateManager(gameState, 1);

        GameResult gameResult = gameManager.play(board);
        Assert.assertThat(gameResult.getCurrentGameState(), Matchers.is(gameState));
    }

    private void configureGameStateManager(final GameState gameState, int numberOfInvocations)
    {
        mockery.checking(new Expectations()
        {{
                exactly(numberOfInvocations).of(gameStateManager).getState(with(any(Board.class)));
                will(returnValue(gameState));
            }});
    }
}
