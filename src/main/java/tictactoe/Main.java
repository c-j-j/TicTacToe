package tictactoe;

import tictactoe.builders.BoardFactory;
import tictactoe.data.Mark;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        boolean inPlay = true;
        while (inPlay)
        {
            System.out.println("Go first(1) or second(2)?");

            int firstOrSecond = scanner.nextInt();

            if (firstOrSecond == 1)
            {
                new Game(new HumanPlayer(Mark.X, new CommandLineRenderer()), new ComputerPlayer(Mark.O)).play(BoardFactory.emptyBoard());
            } else
            {
                new Game(new ComputerPlayer(Mark.O), new HumanPlayer(Mark.X, new CommandLineRenderer())).play(BoardFactory.emptyBoard());
            }

            System.out.println("Play again? Y/N");

            if (scanner.nextLine().contains("N") || scanner.next().contains("n"))
            {
                inPlay = false;
                System.out.println("Game-over");
            }

        }
    }
}
