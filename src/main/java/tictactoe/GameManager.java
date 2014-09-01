package tictactoe;

import tictactoe.builders.BoardBuilder;
import tictactoe.data.Board;
import tictactoe.data.GameResult;
import tictactoe.data.GameState;
import tictactoe.data.Position;
import tictactoe.data.Seed;

public class GameManager
{
    private final GameStateManager gameStateManager;
    private final NextPositionHandler nextPositionHandler;

    public GameManager(GameStateManager gameStateManager, NextPositionHandler nextPositionHandler)
    {
        this.gameStateManager = gameStateManager;
        this.nextPositionHandler = nextPositionHandler;
    }

    public GameResult play(Board board)
    {
        GameState currentGameState = gameStateManager.getState(board);

        if ((currentGameState == GameState.IN_PROGRESS))
        {
            Position nextMove = nextPositionHandler.nextMove(board);

            Board updatedBoard = new BoardBuilder()
                    .withBoard(board)
                    .withMove(nextMove, Seed.COMPUTER)
                    .build();

            return new GameResult(gameStateManager.getState(updatedBoard), updatedBoard);
        }
        else
        {
            return new GameResult(currentGameState, board);
        }
    }
}
