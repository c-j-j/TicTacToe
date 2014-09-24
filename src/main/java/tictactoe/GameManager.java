package tictactoe;

import tictactoe.builders.BoardFactory;
import tictactoe.checkers.BlockForkChecker;
import tictactoe.checkers.ForkChecker;
import tictactoe.checkers.OpponentInCornerChecker;
import tictactoe.data.Board;
import tictactoe.data.GameProgress;
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

    public static GameManager newGameManager()
    {
        return new GameManager(new GameStateManager(), new NextPositionHandler(new ForkChecker(), new BlockForkChecker(), new OpponentInCornerChecker()));
    }

    /**
     *
     * @param seed - represents player which is making first move.
     * @return - GameProgress which contains game state and current board
     */
    public GameProgress start(Seed seed)
    {
        if (seed == Seed.COMPUTER)
        {
            return play(BoardFactory.emptyBoard());
        }
        else if (seed == Seed.OPPONENT)
        {
            return new GameProgress(GameState.IN_PROGRESS, BoardFactory.emptyBoard());
        }
        else
        {
            throw new IllegalArgumentException("Seed must be COMPUTER/OPPONENT");
        }
    }

    public GameProgress play(Board board)
    {
        GameState currentGameState = gameStateManager.getState(board);

        if ((currentGameState == GameState.IN_PROGRESS))
        {
            Position nextMove = nextPositionHandler.nextMove(board);

            Board updatedBoard = BoardFactory.addMove(board, nextMove, Seed.COMPUTER);

            return new GameProgress(gameStateManager.getState(updatedBoard), updatedBoard);
        }
        else
        {
            return new GameProgress(currentGameState, board);
        }
    }
}
