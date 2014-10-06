package tictactoe;

import org.hamcrest.Description;
import org.jmock.Expectations;
import org.jmock.api.Action;
import org.jmock.api.Invocation;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.InvalidMoveException;
import tictactoe.data.Mark;
import tictactoe.data.Position;
import tictactoe.players.Player;
import tictactoe.render.GameRenderer;

public class BoardGameplayTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
    private Player playerA;
    private Player playerB;
    private GameRenderer gameRenderer;


    @Before
    public void setUp() throws Exception
    {
        playerA = mockery.mock(Player.class, "playerA");
        playerB = mockery.mock(Player.class, "playerB");
        gameRenderer = mockery.mock(GameRenderer.class);

    }

    @Test(expected = IllegalArgumentException.class)
    public void returnExceptionIfGameIsInFinalState()
    {
        Board board = new BoardBuilder()
                .withMove(Position.BOTTOM_CENTRE, Mark.O)
                .withMove(Position.BOTTOM_LEFT, Mark.O)
                .withMove(Position.BOTTOM_RIGHT, Mark.O)
                .build();
        board.playGame(playerA, playerB, gameRenderer);
    }

    @Test
    public void onlyPlayerAShouldMakeAMoveIfPlayerAWinsGame()
    {
        Board board = new BoardBuilder()
                .withMove(Position.BOTTOM_CENTRE, Mark.O)
                .withMove(Position.BOTTOM_LEFT, Mark.O)
                .build();

        expectPlayerToPerformAction(board, playerA, Position.BOTTOM_RIGHT, Mark.O);
        expectGameRendererToBeCalled();

        board.playGame(playerA, playerB, gameRenderer);
    }

    @Test
    public void bothPlayersShouldMakeAMoveWhenPlayerADoesNotWinGame()
    {
        Board board = new BoardBuilder()
                .withMove(Position.BOTTOM_CENTRE, Mark.O)
                .withMove(Position.BOTTOM_LEFT, Mark.O)

                .build();

        expectPlayerToPerformAction(board, playerA, Position.TOP_LEFT, Mark.X);
        expectPlayerToPerformAction(board, playerB, Position.BOTTOM_RIGHT, Mark.O);
        expectGameRendererToBeCalled();

        board.playGame(playerA, playerB, gameRenderer);
    }

    @Test(expected = InvalidMoveException.class)
    public void throwRuntimeExceptionWhenAttemptToOverridePosition()
    {
        Board board = new BoardBuilder().withMove(Position.BOTTOM_CENTRE, Mark.O).build();
        board.addMark(Position.BOTTOM_CENTRE, Mark.X);
    }

    private void expectGameRendererToBeCalled()
    {
        mockery.checking(new Expectations()
        {
            {
                atLeast(1).of(gameRenderer).draw(with(any(Board.class)));
            }
        });
    }

    private void expectPlayerToPerformAction(final Board board, final Player player, final Position position, final Mark mark)
    {
        mockery.checking(new Expectations()
        {
            {
                oneOf(player).play(board);
                will(doAll(new Action()
                {
                    @Override
                    public Object invoke(Invocation invocation) throws Throwable
                    {
                        board.addMark(position, mark);
                        return null;
                    }

                    @Override
                    public void describeTo(Description description)
                    {

                    }
                }));
            }
        });
    }
}
