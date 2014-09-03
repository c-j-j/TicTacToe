package tictactoe.integration;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import tictactoe.GameManager;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.GameProgress;
import tictactoe.data.GameState;
import tictactoe.data.Position;
import tictactoe.data.Seed;

public class IntegrationTestBase
{
    private Board currentBoard;
    protected GameManager gameManager;
    private GameProgress gameProgress;

    @Before
    public void setUp() throws Exception
    {
        currentBoard = new BoardBuilder().build();
        gameManager = GameManager.newGameManager();
    }

    protected void computerHasWon()
    {
        Assert.assertThat(gameProgress.getCurrentGameState(), Matchers.is(GameState.COMPUTER_WINS));
    }

    protected void playerGoes(Position position)
    {
        Assert.assertFalse(String.format("Position %s already occupied", position), currentBoard.isPositionOccupied(position));
        currentBoard = new BoardBuilder()
                .withBoard(currentBoard)
                .withMove(position, Seed.OPPONENT)
                .build();
    }

    protected void computerShouldGo(Position expectedPosition)
    {
        gameProgress = gameManager.play(currentBoard);
        Board board = gameProgress.getBoard();
        String currentPositions = board.getPositionsForSeed(Seed.COMPUTER).toString();
        Assert.assertThat(String.format("Expected Position: %S\nActual positions: %S", expectedPosition.toString(), currentPositions),
                board.getSeed(expectedPosition), Matchers.is(Seed.COMPUTER));
        currentBoard = board;
    }

    protected void shouldBeADraw()
    {
        gameProgress = gameManager.play(currentBoard);
        Assert.assertThat(gameProgress.getCurrentGameState(), Matchers.is(GameState.DRAW));
    }
}
