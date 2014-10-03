package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.GameState;
import tictactoe.data.Mark;
import tictactoe.data.Position;

public class GameStateManagerTest
{
    @Test
    public void shouldReturnInProgressWhenGameIsNotWonAndEmptyPositionsAreAvailable()
    {
        Board board = new BoardBuilder()
                .build();

        GameState gameState = new GameStateManager().getState(board);

        Assert.assertThat(gameState, Matchers.is(GameState.IN_PROGRESS));
    }

    //X O O
    //O O X
    //X X O
    @Test
    public void shouldReturnDrawWhenBoardIsEmptyAndNoOneHasWon() throws Exception
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, Mark.X)
                .withMove(Position.TOP_CENTRE, Mark.O)
                .withMove(Position.TOP_RIGHT, Mark.O)
                .withMove(Position.MIDDLE_LEFT, Mark.O)
                .withMove(Position.CENTRE, Mark.O)
                .withMove(Position.MIDDLE_RIGHT, Mark.X)
                .withMove(Position.BOTTOM_LEFT, Mark.X)
                .withMove(Position.BOTTOM_CENTRE, Mark.X)
                .withMove(Position.BOTTOM_RIGHT, Mark.O)
                .build();

        GameState gameState = new GameStateManager().getState(board);

        Assert.assertThat(gameState, Matchers.is(GameState.DRAW));
    }

    @Test
    public void shouldReturnComputerWinWhenComputerHasThreeInRow() throws Exception
    {
        testGameStateCanFindWinner(Mark.X, GameState.COMPUTER_WINS);
    }

    @Test
    public void shouldReturnComputerLosesWhenOpponentHasThreeInRow() throws Exception
    {
        testGameStateCanFindWinner(Mark.O, GameState.COMPUTER_LOSES);
    }

    private void testGameStateCanFindWinner(Mark mark, GameState state)
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, mark)
                .withMove(Position.TOP_CENTRE, mark)
                .withMove(Position.TOP_RIGHT, mark)
                .build();

        GameState gameState = new GameStateManager().getState(board);

        Assert.assertThat(gameState, Matchers.is(state));
    }
}