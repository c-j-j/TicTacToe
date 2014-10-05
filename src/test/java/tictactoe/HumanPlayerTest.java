package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public class HumanPlayerTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery();
    private GameRenderer gameRenderer;

    @Before
    public void setUp() throws Exception
    {
        gameRenderer = mockery.mock(GameRenderer.class);
    }

    @Test
    public void shouldObtainInputFromUserAndUpdateBoardWithChosenMove()
    {
        Board board = new BoardBuilder().build();
        Mark mark = Mark.O;

        final Position position = expectCallToGameRenderer(board, mark);
        new HumanPlayer(mark, gameRenderer).play(board);
        Assert.assertThat(board.getMark(position), Matchers.is(mark));
    }

    private Position expectCallToGameRenderer(final Board board, final Mark mark)
    {
        final Position position = Position.BOTTOM_CENTRE;
        mockery.checking(new Expectations()
        {
            {
                oneOf(gameRenderer).getPositionFromUser(board, mark);
                will(returnValue(position));
            }
        });
        return position;
    }
}