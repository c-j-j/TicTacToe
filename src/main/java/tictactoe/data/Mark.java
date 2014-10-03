package tictactoe.data;

public enum Mark
{
    EMPTY(" "), X("X"), O("O");
    private String string;

    Mark(String string) {
        this.string = string;
    }


    public String getString() {
        return string;
    }
}
