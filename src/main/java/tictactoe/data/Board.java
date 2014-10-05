package tictactoe.data;

import com.google.gson.Gson;

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

    public List<Position> getEmptyPositions() {
        return moves.keySet().stream().filter(p -> moves.get(p) == Mark.EMPTY).collect(Collectors.toList());
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

    public Mark getMark(Position position) {
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
            System.out.printf("%s has won\n", Mark.X.name());
            return GameState.X_WINS;
        } else if (hasSeedWon(Mark.O)) {
            System.out.printf("%s has won\n", Mark.O.name());
            return GameState.O_WINS;
        } else {
            System.out.println("Draw");
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
