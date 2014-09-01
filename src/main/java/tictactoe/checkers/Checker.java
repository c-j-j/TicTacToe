package tictactoe.checkers;

import tictactoe.Board;
import tictactoe.NextMoveResult;
import tictactoe.Seed;

public interface Checker
{
    public NextMoveResult check(Board board, Seed seed);
}
