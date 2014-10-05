package tictactoe.data;

import tictactoe.players.Player;
import tictactoe.render.GameRenderer;

import java.util.*;
import java.util.stream.Collectors;

public class Board
{
    public static final String OUTPUT_LINE_FORMAT = "%s | %s | %s\n";

    private final Map<Position, Mark> moves;

    public static final Set<WinningLine> WINNING_LINES = new HashSet<WinningLine>()
    {{
            add(new WinningLine(Position.TOP_LEFT, Position.TOP_CENTRE, Position.TOP_RIGHT));
            add(new WinningLine(Position.TOP_LEFT, Position.CENTRE, Position.BOTTOM_RIGHT));
            add(new WinningLine(Position.TOP_LEFT, Position.MIDDLE_LEFT, Position.BOTTOM_LEFT));
            add(new WinningLine(Position.TOP_CENTRE, Position.CENTRE, Position.BOTTOM_CENTRE));
            add(new WinningLine(Position.TOP_RIGHT, Position.CENTRE, Position.BOTTOM_LEFT));
            add(new WinningLine(Position.TOP_RIGHT, Position.MIDDLE_RIGHT, Position.BOTTOM_RIGHT));
            add(new WinningLine(Position.MIDDLE_LEFT, Position.CENTRE, Position.MIDDLE_RIGHT));
            add(new WinningLine(Position.BOTTOM_LEFT, Position.BOTTOM_CENTRE, Position.BOTTOM_RIGHT));
        }};


    public Board(Map<Position, Mark> moves)
    {
        this.moves = moves;
    }

    public boolean isPositionOccupied(Position position)
    {
        return !moves.get(position).equals(Mark.EMPTY);
    }

    public List<Position> getEmptyPositions()
    {
        return moves.keySet().stream()
                .filter(p -> moves.get(p) == Mark.EMPTY)
                .sorted((o1, o2) -> Integer.valueOf(o1.getIntegerRepresentation()).compareTo(o2.getIntegerRepresentation()))
                .collect(Collectors.toList());
    }

    public Map<Position, Mark> getMoves()
    {
        return Collections.unmodifiableMap(moves);
    }

    public Mark getMark(Position position)
    {
        return moves.get(position);
    }

    public boolean hasSeedWon(Mark mark)
    {
        boolean foundWinner = false;

        for (WinningLine winningLine : WINNING_LINES)
        {
            foundWinner = doesSeedOccupyWinningLine(mark, winningLine);

            if (foundWinner)
            {
                break;
            }
        }
        return foundWinner;
    }

    private boolean doesSeedOccupyWinningLine(Mark mark, WinningLine winningLine)
    {
        for (Position position : winningLine.getPositions())
        {
            if (moves.get(position) != mark)
            {
                return false;
            }
        }
        return true;
    }

    public boolean isGameOver()
    {
        return hasSeedWon(Mark.X) || hasSeedWon(Mark.O) || getEmptyPositions().size() == 0;
    }

    public GameOutcome result()
    {
        GameOutcome gameOutcome;
        if (hasSeedWon(Mark.X))
        {
            gameOutcome = GameOutcome.X_WINS;
        } else if (hasSeedWon(Mark.O))
        {
            gameOutcome = GameOutcome.O_WINS;
        } else
        {
            gameOutcome = GameOutcome.DRAW;
        }

        return gameOutcome;
    }

    public void addMark(Position position, Mark mark)
    {
        if (!(moves.get(position) == Mark.EMPTY))
        {
            throw new InvalidMoveException(String.format("Position %s already taken.", position.name()));
        }
        moves.put(position, mark);
    }

    public void playGame(Player playerA, Player playerB, GameRenderer gameRenderer)
    {
        if (this.isGameOver())
        {
            throw new IllegalArgumentException("Board provided is already in a final state.");
        }

        gameLoop(playerA, playerB, gameRenderer);
    }

    private void gameLoop(Player playerA, Player playerB, GameRenderer gameRenderer)
    {
        gameRenderer.draw(this);

        while (!this.isGameOver())
        {
            updateBoardWithPlayerMove(playerA, gameRenderer);
            if (this.isGameOver())
            {
                break;
            }

            updateBoardWithPlayerMove(playerB, gameRenderer);
            if (this.isGameOver())
            {
                break;
            }
        }
    }

    private void updateBoardWithPlayerMove(Player player, GameRenderer gameRenderer)
    {
        player.play(this);
        gameRenderer.draw(this);
    }

    public String toString()
    {
        return new BoardToStringBuilder()
                .withHorizontalMarks(moves.get(Position.TOP_LEFT), moves.get(Position.TOP_CENTRE), moves.get(Position.TOP_RIGHT))
                .drawDividingLine()
                .withHorizontalMarks(moves.get(Position.MIDDLE_LEFT), moves.get(Position.CENTRE), moves.get(Position.MIDDLE_RIGHT))
                .drawDividingLine()
                .withHorizontalMarks(moves.get(Position.BOTTOM_LEFT), moves.get(Position.BOTTOM_CENTRE), moves.get(Position.BOTTOM_RIGHT))
                .build();
    }

    private class BoardToStringBuilder
    {
        String boardStringRepresentation = "";

        public BoardToStringBuilder withHorizontalMarks(Mark mark1, Mark mark2, Mark mark3)
        {
            boardStringRepresentation = boardStringRepresentation + String.format(OUTPUT_LINE_FORMAT, mark1.getString(), mark2.getString(), mark3.getString());
            return this;
        }

        public BoardToStringBuilder drawDividingLine()
        {
            boardStringRepresentation = boardStringRepresentation + "---------\n";
            return this;
        }

        public String build()
        {
            return boardStringRepresentation;
        }
    }
}
