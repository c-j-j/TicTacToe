package tictactoe.render;

import tictactoe.data.Board;
import tictactoe.data.GameOutcome;
import tictactoe.data.Mark;
import tictactoe.data.Position;

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
        System.out.println(board);
    }

    @Override
    public void displayResult(GameOutcome gameOutcome)
    {
        System.out.println(gameOutcome.getMessage());
    }

    @Override
    public Position getPositionFromUser(Board board, Mark mark)
    {
        boolean invalidInputProvided = true;
        int userSpecifiedInteger = 0;
        while (invalidInputProvided)
        {
            System.out.printf("Your move (You are %s). Enter number corresponding to your move.\n", mark.name());
            for (Position position : board.getEmptyPositions())
            {
                System.out.printf("%s:%d,", position.name(), position.getIntegerRepresentation());
            }
            System.out.println();

            userSpecifiedInteger = new Scanner(inputStream).nextInt();

            if (InputValidator.isValid(userSpecifiedInteger, board.getEmptyPositions()))
            {
                invalidInputProvided = false;
            } else
            {
                System.out.println("Invalid number entered. Choose from list below");
            }

        }
        return Position.getPosition(userSpecifiedInteger);

    }
}
