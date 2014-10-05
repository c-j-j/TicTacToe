package tictactoe.data;

import java.util.HashMap;
import java.util.Map;

public enum Mark
{
    EMPTY(" "), X("X"), O("O");
    private String string;

    public static Map<Mark, Mark> OTHER_MARK_MAP = new HashMap<Mark, Mark>(){{
        put(Mark.X, Mark.O);
        put(Mark.O, Mark.X);
        put(Mark.EMPTY, Mark.EMPTY);
    }};

    Mark(String string) {
        this.string = string;
    }


    public String getString() {
        return string;
    }
}
