package tictactoe.checkers;

import tictactoe.Board;
import tictactoe.Result;
import tictactoe.Seed;

public class OpponentCornerChecker implements Checker {

    /**
     * seed - represents opponent which you'd like to place in the opposite corner
     */
    @Override
    public Result check(Board board, Seed seed) {

        Board.CORNERS.stream()
                .filter(corner -> board.getSeed(corner) == seed && board.getSeed(PositionUtils.getOppositeCorner(corner)) == Seed.EMPTY)
                .forEach(corner -> new Result(PositionUtils.getOppositeCorner(corner)));
        return Result.indeterminateResult();
    }
}
