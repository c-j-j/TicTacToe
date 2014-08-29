package tictactoe.checkers;

import tictactoe.Board;
import tictactoe.Result;
import tictactoe.Seed;

public interface Checker
{
    public Result check(Board board, Seed seed);
}
