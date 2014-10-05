package tictactoe;

import tictactoe.builders.BoardFactory;
import tictactoe.data.Mark;
import tictactoe.players.ComputerPlayer;
import tictactoe.players.HumanPlayer;
import tictactoe.render.CommandLineRenderer;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        boolean inPlay = true;
        while (inPlay)
        {
            if (checkIfUserWantsToGoFirstOrSecond(scanner) == 1)
            {
                new Game(new CommandLineRenderer()).play(BoardFactory.emptyBoard(), new HumanPlayer(Mark.X, new CommandLineRenderer()), new ComputerPlayer(Mark.O));
            } else
            {
                new Game(new CommandLineRenderer()).play(BoardFactory.emptyBoard(), new ComputerPlayer(Mark.O), new HumanPlayer(Mark.X, new CommandLineRenderer()));
            }

            inPlay = checkIfUserWantsAnotherGame(scanner);
        }
    }

    private static int checkIfUserWantsToGoFirstOrSecond(Scanner scanner)
    {
        System.out.println("Go first(1) or second(2)?");

        return scanner.nextInt();
    }

    private static boolean checkIfUserWantsAnotherGame(Scanner scanner)
    {
        System.out.println("Play again? Y/N");

        if (scanner.nextLine().contains("N") || scanner.next().contains("n"))
        {
            System.out.println("Game-over");
            return false;
        }
        return true;
    }
}
