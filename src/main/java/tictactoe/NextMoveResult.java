package tictactoe;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class NextMoveResult
{
    private final List<Position> nextMoves;

    public NextMoveResult(List<Position> nextMoves) {
        this.nextMoves = nextMoves;
    }

    public NextMoveResult(Position position) {
        this(Lists.newArrayList(position));
    }

    public static NextMoveResult indeterminateResult() {
        return new NextMoveResult(Collections.emptyList());
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
