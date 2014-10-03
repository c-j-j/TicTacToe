package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.Sequence;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.data.Board;
import tictactoe.data.GameProgress;
import tictactoe.data.GameState;

public class GameTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
    private Player playerA;
    private Player playerB;
    private Board board;
    private Game game;
    private GameProgress simulatedGameProgress;
    private Sequence boardInvocationSequence;

    @Before
    public void setUp() throws Exception
    {
        playerA = mockery.mock(Player.class, "playerA");
        playerB = mockery.mock(Player.class, "playerB");

        board = mockery.mock(Board.class, "board");

        game = new Game(playerA, playerB);
        simulatedGameProgress = new GameProgress(GameState.DRAW, board);

        boardInvocationSequence = mockery.sequence("boardInvocationSequence");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfGameIsAlreadyOver()
    {
        expectPlayerToInvoked(playerA, 0);
        expectPlayerToInvoked(playerB, 0);

        configureIfBoardIsInGameOverState(true);
        game.play(board);
    }

    @Test
    public void shouldOnlyInvokePlayerAIfPlayerACausesBoardToGoToGameOverState()
    {
        expectPlayerToInvoked(playerA, 1);
        expectPlayerToInvoked(playerB, 0);

        configureIfBoardIsInGameOverState(false, false, true);

        configureResultFromBoard(simulatedGameProgress);
       // GameProgress actualGameProgress = game.play(board);

        //Assert.assertThat(actualGameProgress, Matchers.is(simulatedGameProgress));
    }

    @Test
    public void shouldOnlyInvokePlayerAAndBWhenBoardIsInNonGameOverState()
    {
        expectPlayerToInvoked(playerA, 1);
        expectPlayerToInvoked(playerB, 1);

        configureIfBoardIsInGameOverState(false, false, false, true);

        configureResultFromBoard(simulatedGameProgress);
      //  GameProgress actualGameProgress = game.play(board);

      //  Assert.assertThat(actualGameProgress, Matchers.is(simulatedGameProgress));
    }

    private void configureResultFromBoard(final GameProgress gameProgress)
    {
        mockery.checking(new Expectations()
        {
            {
                oneOf(board).result();
                will(returnValue(gameProgress));
            }
        });
    }

    private void configureIfBoardIsInGameOverState(final boolean... sequenceOfResults)
    {
        mockery.checking(new Expectations()
        {
            {
                for (boolean result : sequenceOfResults)
                {
                    oneOf(board).isGameOver();
                    will(returnValue(result));
                    inSequence(boardInvocationSequence);
                }
            }
        });
    }

    private void expectPlayerToInvoked(final Player player, final int countOfInvocations)
    {
        mockery.checking(new Expectations()
        {
            {
                exactly(countOfInvocations).of(player).play(with(any(Board.class)));
            }
        });
    }
}