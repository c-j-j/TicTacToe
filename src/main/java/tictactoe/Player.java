package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameProgress;

public interface Player {
    GameProgress play(Board board);
}
