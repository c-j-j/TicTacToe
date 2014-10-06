package tictactoe.players;

import tictactoe.builders.BoardFactory;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.Position;

import java.util.HashMap;
import java.util.Map;

public class ComputerPlayer implements Player
{
    public static final int THIS_PLAYER_HAS_WON_SCORE = 10;
    public static final int OTHER_PLAYER_HAS_WON_SCORE = -10;
    public static final int DRAW_SCORE = 0;
    private Mark mark;

    public ComputerPlayer(Mark mark)
    {
        this.mark = mark;
    }

    @Override
    public void play(Board board)
    {
        updateBoardUsingMinimax(board);
    }

    private void updateBoardUsingMinimax(Board board)
    {
        minimax(board, true);
    }

    private int minimax(Board board, boolean thisPlayersTurn)
    {
        if (board.isGameOver())
        {
            return calculateScore(board);
        }

        Map<Position, Integer> positionScores = new HashMap<>();

        for (Position emptyPosition : board.getEmptyPositions())
        {
            positionScores.put(emptyPosition, minimax(BoardFactory.addMove(board, emptyPosition, determineMark(thisPlayersTurn)), !thisPlayersTurn));
        }

        if (thisPlayersTurn)
        {
            board.addMark(getPositionWithMaximumScore(positionScores), mark);
            return getMaximumScore(positionScores);
        } else
        {
            return getMinimumScore(positionScores);
        }
    }

    private int getMinimumScore(Map<Position, Integer> positionScores)
    {
        return positionScores.values().stream().min(Integer::compare).get();
    }

    private int getMaximumScore(Map<Position, Integer> positionScores)
    {
        return positionScores.values().stream().max(Integer::compare).get();
    }

    private Position getPositionWithMaximumScore(Map<Position, Integer> positionScores)
    {
        for (Position position : positionScores.keySet())
        {
            if (positionScores.get(position) == getMaximumScore(positionScores))
            {
                return position;
            }
        }

        throw new ComputerPlayerException("Computer has not determined best position from list of states");
    }

    private Mark determineMark(boolean thisPlayersTurn)
    {
        if (thisPlayersTurn)
        {
            return mark;
        } else
        {
            return Mark.OTHER_MARK_MAP.get(mark);
        }
    }

    private int calculateScore(Board board)
    {
        if (board.hasSeedWon(mark))
        {
            return THIS_PLAYER_HAS_WON_SCORE;
        } else if (board.hasSeedWon(Mark.OTHER_MARK_MAP.get(mark)))
        {
            return OTHER_PLAYER_HAS_WON_SCORE;
        } else
        {
            return DRAW_SCORE;
        }
    }
}
