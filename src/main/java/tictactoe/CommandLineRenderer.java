package tictactoe;

import tictactoe.data.Board;
import tictactoe.data.GameProgress;
import tictactoe.data.Mark;
import tictactoe.data.Position;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class CommandLineRenderer implements GameRenderer
{

    private InputStream inputStream;

    public CommandLineRenderer(InputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public CommandLineRenderer()
    {
        this(System.in);
    }

    @Override
    public void draw(Board board)
    {
        System.out.print(board + "\r");
    }

    @Override
    public void endResult(GameProgress result)
    {

    }

    @Override
    public boolean newGameQuery()
    {
        return false;
    }

    @Override
    public Position getPositionFromUser(Board board, Mark mark)
    {
        System.out.printf("Your move (You are %s). Enter number corresponding to your move.\n", mark.name());
        for (Position position : board.getEmptyPositions())
        {
            System.out.printf("%s:%d,", position.name(), position.getIntegerRepresentation());
        }
        System.out.println();

        int userSpecifiedInteger = new Scanner(inputStream).nextInt();

        return Position.getPosition(userSpecifiedInteger);
    }
}
