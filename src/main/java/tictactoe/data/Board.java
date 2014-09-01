package tictactoe.data;

import com.google.gson.annotations.Expose;

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

    @Expose
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
        Set<WinningLine> winningLinesForPosition = getPotentialWinningLinesForAllPositions(getPositionsForSeed(seed));

        List<Position> winningPositions = new ArrayList<>();

        for (WinningLine winningLine : winningLinesForPosition)
        {

            int count = countSeedOnWinningLine(winningLine, seed);

            if (count == BoardPositions.POTENTIAL_WIN)
            {
                Optional<Position> emptyPosition = doesEmptyExistOnLine(winningLine);

                if (emptyPosition.isPresent())
                {
                    winningPositions.add(emptyPosition.get());
                }
            }
        }
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

    private Optional<Position> doesEmptyExistOnLine(WinningLine winningLine)
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

}
