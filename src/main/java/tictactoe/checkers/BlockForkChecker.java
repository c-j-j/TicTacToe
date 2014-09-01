package tictactoe.checkers;

import tictactoe.Board;
import tictactoe.Position;
import tictactoe.Result;
import tictactoe.Seed;
import tictactoe.builders.BoardBuilder;
import tictactoe.utils.SeedUtils;

import java.util.ArrayList;
import java.util.List;

public class BlockForkChecker implements Checker
{
    @Override
    public Result check(Board board, Seed seed)
    {
        Result result = new ForkChecker().check(board, SeedUtils.getOtherPlayer(seed));

        List<Position> possibleForkingPositions = result.getNextMoves();
        if (possibleForkingPositions.size() == 1)
        {
            return new Result(possibleForkingPositions.get(0));
        }
        else if (possibleForkingPositions.size() > 1)
        {
            return forceOtherPlayerToGoInNonForkingPosition(possibleForkingPositions, board);
        }
        else
        {
            return Result.indeterminateResult();
        }
    }

    private Result forceOtherPlayerToGoInNonForkingPosition(List<Position> forkingPositions, Board board)
    {
        List<Position> potentialNewPositions = new ArrayList<>();

        for (Position emptyPosition : board.getEmptyPositions())
        {
            Board boardWithAdditionalMove = new BoardBuilder()
                    .withBoard(board)
                    .withMove(emptyPosition, Seed.COMPUTER)
                    .build();

            if (willForceOpponentToNonForkingPosition(forkingPositions, emptyPosition, boardWithAdditionalMove))
            {
                potentialNewPositions.add(emptyPosition);
            }

        }
        return new Result(potentialNewPositions);
    }

    private boolean willForceOpponentToNonForkingPosition(List<Position> forkingPositions, Position emptyPosition, Board boardWithAdditionalMove)
    {
        return boardWithAdditionalMove.canSeedWin(Seed.COMPUTER).hasBeenDetermined()
                && !forkingPositions.contains(emptyPosition);
    }
}
