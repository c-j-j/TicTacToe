package tictactoe;

import tictactoe.builders.BoardFactory;
import tictactoe.data.Mark;
import tictactoe.players.ComputerPlayer;
import tictactoe.players.HumanPlayer;
import tictactoe.render.CommandLineRenderer;
import tictactoe.render.GameRenderer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean inPlay = true;
        GameRenderer gameRenderer = new CommandLineRenderer();
        Game game = new Game(gameRenderer);

        while (inPlay) {
            if (checkIfUserWantsToGoFirstOrSecond(scanner) == 1) {
                game.play(BoardFactory.emptyBoard(), new HumanPlayer(Mark.X, gameRenderer), new ComputerPlayer(Mark.O));
            } else {
                game.play(BoardFactory.emptyBoard(), new ComputerPlayer(Mark.O), new HumanPlayer(Mark.X, gameRenderer));
            }

            inPlay = checkIfUserWantsAnotherGame(scanner);
        }
    }

    private static int checkIfUserWantsToGoFirstOrSecond(Scanner scanner) {
        System.out.println("Go first(1) or second(2)?");

        return scanner.nextInt();
    }

    private static boolean checkIfUserWantsAnotherGame(Scanner scanner) {
        System.out.println("Play again? Y/N");

        if (scanner.nextLine().contains("N") || scanner.next().contains("n")) {
            System.out.println("Game-over");
            return false;
        }
        return true;
    }
}
