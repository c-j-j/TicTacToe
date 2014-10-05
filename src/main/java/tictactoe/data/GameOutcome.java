package tictactoe.data;

public enum GameOutcome
{
    X_WINS("X has won"), O_WINS("O has won"), DRAW("Draw");
    private String message;

    GameOutcome(String message) {this.message = message;}

    public String getMessage()
    {
        return message;
    }
}
