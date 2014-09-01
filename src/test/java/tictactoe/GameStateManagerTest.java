package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;

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
                .withMove(Position.TOP_LEFT, Seed.COMPUTER)
                .withMove(Position.TOP_CENTRE, Seed.OPPONENT)
                .withMove(Position.TOP_RIGHT, Seed.OPPONENT)
                .withMove(Position.MIDDLE_LEFT, Seed.OPPONENT)
                .withMove(Position.CENTRE, Seed.OPPONENT)
                .withMove(Position.MIDDLE_RIGHT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_LEFT, Seed.COMPUTER)
                .withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER)
                .withMove(Position.BOTTOM_RIGHT, Seed.OPPONENT)
                .build();

        GameState gameState = new GameStateManager().getState(board);

        Assert.assertThat(gameState, Matchers.is(GameState.DRAW));
    }

    @Test
    public void shouldReturnComputerWinWhenComputerHasThreeInRow() throws Exception
    {
        testGameStateCanFindWinner(Seed.COMPUTER, GameState.COMPUTER_WINS);
    }

    @Test
    public void shouldReturnComputerLosesWhenOpponentHasThreeInRow() throws Exception
    {
        testGameStateCanFindWinner(Seed.OPPONENT, GameState.COMPUTER_LOSES);
    }

    private void testGameStateCanFindWinner(Seed seed, GameState state)
    {
        Board board = new BoardBuilder()
                .withMove(Position.TOP_LEFT, seed)
                .withMove(Position.TOP_CENTRE, seed)
                .withMove(Position.TOP_RIGHT, seed)
                .build();

        GameState gameState = new GameStateManager().getState(board);

        Assert.assertThat(gameState, Matchers.is(state));
    }
}