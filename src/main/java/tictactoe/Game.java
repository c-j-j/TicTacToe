package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameOutcome;
import tictactoe.players.Player;
import tictactoe.render.GameRenderer;

public class Game
{
    private final GameRenderer gameRenderer;

    public Game(GameRenderer gameRenderer)
    {
        this.gameRenderer = gameRenderer;
    }

    public GameOutcome play(Board board, Player playerA, Player playerB)
    {
        board.playGame(playerA, playerB, gameRenderer);
        GameOutcome gameOutcome = board.result();
        gameRenderer.displayResult(gameOutcome);
        return gameOutcome;
    }
}
