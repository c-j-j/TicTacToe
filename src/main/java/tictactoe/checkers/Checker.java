package tictactoe.checkers;

import tictactoe.data.Board;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Seed;

public interface Checker
{
    public NextMoveResult check(Board board, Seed seed);
}
