package tictactoe;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class Result {
    private final List<Position> nextMoves;

    public Result(List<Position> nextMoves) {
        this.nextMoves = nextMoves;
    }

    public Result(Position position) {
        this(Lists.newArrayList(position));
    }

    public static Result indeterminateResult() {
        return new Result(Collections.emptyList());
    }

    public boolean hasBeenDetermined() {
        return nextMoves.size() > 0;
    }

    public Position getNextMove() {
        return nextMoves.get(0);
    }

    public List<Position> getNextMoves() {
        return nextMoves;
    }
}
