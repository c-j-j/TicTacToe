package tictactoe;

import tictactoe.builders.BoardFactory;
import tictactoe.checkers.BlockForkChecker;
import tictactoe.checkers.ForkChecker;
import tictactoe.checkers.OpponentInCornerChecker;
import tictactoe.data.*;
import tictactoe.data.Mark;

public class GameManager
{
    private final GameStateManager gameStateManager;
    private final NextPositionHandler nextPositionHandler;

    public GameManager(GameStateManager gameStateManager, NextPositionHandler nextPositionHandler)
    {
        this.gameStateManager = gameStateManager;
        this.nextPositionHandler = nextPositionHandler;
    }

    public static GameManager newGameManager()
    {
        return new GameManager(new GameStateManager(), new NextPositionHandler(new ForkChecker(), new BlockForkChecker(), new OpponentInCornerChecker()));
    }

    /**
     *
     * @param mark - represents player which is making first move.
     * @return - GameProgress which contains game state and current board
     */
    public GameProgress start(Mark mark)
    {
        if (mark == Mark.X)
        {
            return play(BoardFactory.emptyBoard());
        }
        else if (mark == Mark.O)
        {
            return new GameProgress(GameState.IN_PROGRESS, BoardFactory.emptyBoard());
        }
        else
        {
            throw new IllegalArgumentException("Mark must be X/O");
        }
    }

    public GameProgress play(Board board)
    {
        GameState currentGameState = gameStateManager.getState(board);

        if ((currentGameState == GameState.IN_PROGRESS))
        {
            Position nextMove = nextPositionHandler.nextMove(board);

            Board updatedBoard = BoardFactory.addMove(board, nextMove, Mark.X);

            return new GameProgress(gameStateManager.getState(updatedBoard), updatedBoard);
        }
        else
        {
            return new GameProgress(currentGameState, board);
        }
    }
}
