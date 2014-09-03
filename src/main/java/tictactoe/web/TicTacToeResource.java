package tictactoe.web;

import tictactoe.GameManager;
import tictactoe.builders.BoardFactory;
import tictactoe.data.Board;
import tictactoe.data.Position;
import tictactoe.data.Seed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path(TicTacToeResource.ROOT)
@Produces(MediaType.APPLICATION_JSON)
public class TicTacToeResource
{
    public static final String ROOT = "ttt";
    public static final String NEW_GAME = "new_game";
    public static final String FIRST_PLAYER = "firstPlayer";
    public static final String NEXT_POSITION = "nextPosition";
    public static final String BOARD = "board";
    public static final String NEXT_MOVE = "nextMove";

    @Context
    private GameManager gameManager;

    @GET
    @Path(NEW_GAME)
    public String newGame(@QueryParam(FIRST_PLAYER) Seed seed)
    {
        return gameManager.start(seed).toJson();
    }

    @GET
    @Path(NEXT_MOVE)
    public String playNextMove(@QueryParam(NEXT_POSITION) Position position, @QueryParam(BOARD) String board)
    {
        return gameManager.play(BoardFactory.addMove(Board.inflateFromJson(board), position, Seed.OPPONENT)).toJson();
    }
}
