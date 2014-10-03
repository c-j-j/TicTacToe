package tictactoe.data;

import com.google.gson.Gson;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Board {
    public static final String OUTPUT_LINE_FORMAT = "%s | %s | %s\n";

    private final Map<Position, Mark> moves;

    public Board(Map<Position, Mark> moves) {
        this.moves = moves;
    }

    public boolean isPositionOccupied(Position position) {
        return !moves.get(position).equals(Mark.EMPTY);
    }

    public Position findEmptyCorner() {
        for (Position corner : BoardPositions.CORNERS) {
            if (moves.get(corner) == Mark.EMPTY) {
                return corner;
            }
        }
        return null;
    }

    public Position findEmptySide() {
        for (Position side : BoardPositions.SIDES) {
            if (moves.get(side) == Mark.EMPTY) {
                return side;
            }
        }
        return null;
    }

    public NextMoveResult canSeedWin(Mark mark) {
        List<Position> winningPositions = new ArrayList<>();

        for (WinningLine winningLine : getPotentialWinningLinesForAllPositions(getPositionsForSeed(mark))) {
            if (countSeedOnWinningLine(winningLine, mark) == BoardPositions.POTENTIAL_WIN) {
                Optional<Position> emptyPosition = doesEmptyPositionExistOnLine(winningLine);

                if (emptyPosition.isPresent()) {
                    winningPositions.add(emptyPosition.get());
                }
            }
        }

        return calculateResultFromWinningPositions(winningPositions);
    }

    private NextMoveResult calculateResultFromWinningPositions(List<Position> winningPositions) {
        if (winningPositions.size() > 0) {
            return new NextMoveResult(winningPositions);
        } else {
            return NextMoveResult.indeterminateResult();
        }
    }

    private int countSeedOnWinningLine(WinningLine winningLine, Mark mark) {
        int count = 0;

        for (Position position : winningLine.getPositions()) {
            if (moves.get(position) == mark) {
                count++;
            }
        }
        return count;
    }

    private Optional<Position> doesEmptyPositionExistOnLine(WinningLine winningLine) {
        return winningLine.getPositions()
                .stream()
                .filter(
                        position -> moves.get(position) == Mark.EMPTY).findFirst();
    }

    public List<Position> getEmptyPositions() {
        return moves.keySet().stream().filter(p -> moves.get(p) == Mark.EMPTY).collect(Collectors.toList());
    }

    private Set<WinningLine> getPotentialWinningLinesForAllPositions(List<Position> positions) {

        Set<WinningLine> winningLines = new HashSet<>();

        for (Position position : positions) {
            winningLines.addAll(BoardPositions.WINNING_LINES
                    .stream()
                    .filter(winningLine -> winningLine.contains(position))
                    .collect(Collectors.toSet()));
        }
        return winningLines;
    }

    public List<Position> getPositionsForSeed(Mark mark) {
        return moves.keySet()
                .stream()
                .filter(position -> moves.get(position) == mark)
                .collect(Collectors.toList());
    }

    public Map<Position, Mark> getMoves() {
        return Collections.unmodifiableMap(moves);
    }

    public Mark getSeed(Position position) {
        return moves.get(position);
    }

    public boolean hasSeedWon(Mark mark) {
        boolean foundWinner = false;

        for (WinningLine winningLine : BoardPositions.WINNING_LINES) {
            foundWinner = doesSeedOccupyWinningLine(mark, winningLine);

            if (foundWinner) {
                break;
            }
        }
        return foundWinner;
    }

    private boolean doesSeedOccupyWinningLine(Mark mark, WinningLine winningLine) {
        for (Position position : winningLine.getPositions()) {
            if (moves.get(position) != mark) {
                return false;
            }
        }
        return true;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static Board inflateFromJson(String json) {
        return new Gson().fromJson(json, Board.class);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return !(moves != null ? !moves.equals(board.moves) : board.moves != null);
    }

    @Override
    public int hashCode() {
        return moves != null ? moves.hashCode() : 0;
    }

    public boolean hasNoEmptySpaces() {
        return getEmptyPositions().size() == 0;
    }

    public boolean isGameOver() {
        return hasSeedWon(Mark.X) || hasSeedWon(Mark.O) || hasNoEmptySpaces();
    }

    public GameState result() {
        if (hasSeedWon(Mark.X)) {
            return GameState.COMPUTER_LOSES;
        } else if (hasSeedWon(Mark.O)) {
            return GameState.COMPUTER_WINS;
        } else {
            return GameState.DRAW;
        }
    }

    public void addMark(Mark mark, Position position) {
        moves.put(position, mark);
    }

    public String toString() {
        return new BoardToStringBuilder()
                .withHorizontalMarks(moves.get(Position.TOP_LEFT), moves.get(Position.TOP_CENTRE), moves.get(Position.TOP_RIGHT))
                .drawDividingLine()
                .withHorizontalMarks(moves.get(Position.MIDDLE_LEFT), moves.get(Position.CENTRE), moves.get(Position.MIDDLE_RIGHT))
                .drawDividingLine()
                .withHorizontalMarks(moves.get(Position.BOTTOM_LEFT), moves.get(Position.BOTTOM_CENTRE), moves.get(Position.BOTTOM_RIGHT))
                .build();
    }

    private class BoardToStringBuilder {
        String boardStringRepresentation = "";

        public BoardToStringBuilder withHorizontalMarks(Mark mark1, Mark mark2, Mark mark3) {
            boardStringRepresentation = boardStringRepresentation + String.format(OUTPUT_LINE_FORMAT, mark1.getString(), mark2.getString(), mark3.getString());
            return this;
        }

        public BoardToStringBuilder drawDividingLine() {
            boardStringRepresentation = boardStringRepresentation + "---------\n";
            return this;
        }

        public String build() {
            return boardStringRepresentation;
        }
    }
}
