package tictactoe.checkers;

import tictactoe.builders.BoardFactory;
import tictactoe.data.Board;
import tictactoe.data.Mark;
import tictactoe.data.NextMoveResult;
import tictactoe.data.Position;
import tictactoe.utils.MarkUtils;

import java.util.ArrayList;
import java.util.List;

public class BlockForkChecker implements Checker
{

    private final ForkChecker forkChecker = new ForkChecker();

    @Override
    public NextMoveResult check(Board board, Mark mark)
    {
        NextMoveResult nextMoveResult = forkChecker.check(board, MarkUtils.getOtherMark(mark));

        List<Position> possibleForkingPositions = nextMoveResult.getNextMoves();
        if (possibleForkingPositions.size() == 1)
        {
            return new NextMoveResult(possibleForkingPositions.get(0));
        }
        else if (possibleForkingPositions.size() > 1)
        {
            return forceOtherPlayerToGoInNonForkingPosition(possibleForkingPositions, board);
        }
        else
        {
            return NextMoveResult.indeterminateResult();
        }
    }

    private NextMoveResult forceOtherPlayerToGoInNonForkingPosition(List<Position> forkingPositions, Board board)
    {
        List<Position> potentialNewPositions = new ArrayList<>();

        for (Position emptyPosition : board.getEmptyPositions())
        {
            Board boardWithAdditionalMove = BoardFactory.addMove(board, emptyPosition, Mark.X);

            if (willForceOpponentToNonForkingPosition(forkingPositions, boardWithAdditionalMove))
            {
                potentialNewPositions.add(emptyPosition);
            }

        }
        return new NextMoveResult(potentialNewPositions);
    }

    private boolean willForceOpponentToNonForkingPosition(List<Position> forkingPositions, Board boardWithAdditionalMove)
    {
        NextMoveResult nextMoveResult = boardWithAdditionalMove.canSeedWin(Mark.X);
        if (nextMoveResult.hasBeenDetermined())
        {
            if (!forkingPositions.contains(nextMoveResult.getNextMove()))
            {
                return true;
            }
        }
        return false;
    }
}
