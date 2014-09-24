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

public class Board
{
    private final Map<Position, Seed> moves;

    public Board(Map<Position, Seed> moves)
    {
        this.moves = moves;
    }

    public boolean isPositionOccupied(Position position)
    {
        return !moves.get(position).equals(Seed.EMPTY);
    }

    public Position findEmptyCorner()
    {
        for (Position corner : BoardPositions.CORNERS)
        {
            if (moves.get(corner) == Seed.EMPTY)
            {
                return corner;
            }
        }
        return null;
    }

    public Position findEmptySide()
    {
        for (Position side : BoardPositions.SIDES)
        {
            if (moves.get(side) == Seed.EMPTY)
            {
                return side;
            }
        }
        return null;
    }

    public NextMoveResult canSeedWin(Seed seed)
    {
        List<Position> winningPositions = new ArrayList<>();

        for (WinningLine winningLine : getPotentialWinningLinesForAllPositions(getPositionsForSeed(seed)))
        {
            if (countSeedOnWinningLine(winningLine, seed) == BoardPositions.POTENTIAL_WIN)
            {
                Optional<Position> emptyPosition = doesEmptyPositionExistOnLine(winningLine);

                if (emptyPosition.isPresent())
                {
                    winningPositions.add(emptyPosition.get());
                }
            }
        }

        return calculateResultFromWinningPositions(winningPositions);
    }

    private NextMoveResult calculateResultFromWinningPositions(List<Position> winningPositions)
    {
        if (winningPositions.size() > 0)
        {
            return new NextMoveResult(winningPositions);
        }
        else
        {
            return NextMoveResult.indeterminateResult();
        }
    }

    private int countSeedOnWinningLine(WinningLine winningLine, Seed seed)
    {
        int count = 0;

        for (Position position : winningLine.getPositions())
        {
            if (moves.get(position) == seed)
            {
                count++;
            }
        }
        return count;
    }

    private Optional<Position> doesEmptyPositionExistOnLine(WinningLine winningLine)
    {
        return winningLine.getPositions()
                .stream()
                .filter(
                        position -> moves.get(position) == Seed.EMPTY).findFirst();
    }

    public List<Position> getEmptyPositions()
    {
        return moves.keySet().stream().filter(p -> moves.get(p) == Seed.EMPTY).collect(Collectors.toList());
    }

    private Set<WinningLine> getPotentialWinningLinesForAllPositions(List<Position> positions)
    {

        Set<WinningLine> winningLines = new HashSet<>();

        for (Position position : positions)
        {
            winningLines.addAll(BoardPositions.WINNING_LINES
                    .stream()
                    .filter(winningLine -> winningLine.contains(position))
                    .collect(Collectors.toSet()));
        }
        return winningLines;
    }

    public List<Position> getPositionsForSeed(Seed seed)
    {
        return moves.keySet()
                .stream()
                .filter(position -> moves.get(position) == seed)
                .collect(Collectors.toList());
    }

    public Map<Position, Seed> getMoves()
    {
        return Collections.unmodifiableMap(moves);
    }

    public Seed getSeed(Position position)
    {
        return moves.get(position);
    }

    public boolean hasSeedWon(Seed seed)
    {
        boolean foundWinner = false;

        for (WinningLine winningLine : BoardPositions.WINNING_LINES)
        {
            foundWinner = doesSeedOccupyWinningLine(seed, winningLine);

            if (foundWinner)
            {
                break;
            }
        }
        return foundWinner;
    }

    private boolean doesSeedOccupyWinningLine(Seed seed, WinningLine winningLine)
    {
        for (Position position : winningLine.getPositions())
        {
            if (moves.get(position) != seed)
            {
                return false;
            }
        }
        return true;
    }

    public String toJson()
    {
        return new Gson().toJson(this);
    }

    public static Board inflateFromJson(String json)
    {
        return new Gson().fromJson(json, Board.class);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        return !(moves != null ? !moves.equals(board.moves) : board.moves != null);

    }

    @Override
    public int hashCode()
    {
        return moves != null ? moves.hashCode() : 0;
    }

    public boolean hasNoEmptySpaces()
    {
        return getEmptyPositions().size() == 0;
    }
}
