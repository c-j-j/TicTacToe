package tictactoe.checkers;

import tictactoe.data.Board;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Mark;

public interface Checker
{
    public NextMoveResult check(Board board, Mark mark);
}
