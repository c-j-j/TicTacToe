package tictactoe.data;

import com.google.common.collect.Lists;

import java.util.List;

public class WinningLine {
    private final Position positionA;
    private final Position positionB;
    private final Position positionC;

    public WinningLine(Position positionA, Position positionB, Position positionC) {

        this.positionA = positionA;
        this.positionB = positionB;
        this.positionC = positionC;
    }

    public List<Position> getPositions() {
        return Lists.newArrayList(positionA, positionB, positionC);
    }

    public boolean contains(Position position) {
        return positionA == position || positionB == position || positionC == position;
    }
}
