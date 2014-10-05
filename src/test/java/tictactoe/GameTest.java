package tictactoe;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.data.Board;
import tictactoe.data.GameOutcome;
import tictactoe.players.Player;
import tictactoe.render.GameRenderer;

@Ignore
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
    private GameRenderer gameRenderer;

    @Before
    public void setUp() throws Exception
    {
        board = mockery.mock(Board.class);
        playerA = mockery.mock(Player.class, "playerA");
        playerB = mockery.mock(Player.class, "playerB");
        gameRenderer = mockery.mock(GameRenderer.class);
    }

    @Test
    public void shouldDelegateGamePlayToBoardObject()
    {
        GameOutcome gameOutcome = GameOutcome.DRAW;
        expectCallToBoard(gameOutcome);
        expectCallToRenderer(gameOutcome);
        GameOutcome returnedGameOutcome = new Game(gameRenderer).play(board, playerA, playerB);

        Assert.assertThat(returnedGameOutcome, Matchers.is(gameOutcome));
    }

    private void expectCallToRenderer(GameOutcome gameOutcome)
    {
        mockery.checking(new Expectations()
        {
            {
                oneOf(gameRenderer).displayResult(gameOutcome);
            }
        });
    }

    private void expectCallToBoard(final GameOutcome gameOutcome)
    {
        mockery.checking(new Expectations()
        {
            {
                oneOf(board).playGame(playerA, playerB, gameRenderer);
                oneOf(board).result();
                will(returnValue(gameOutcome));
            }
        });
    }
}