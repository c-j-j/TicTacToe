package tictactoe.utils;

import tictactoe.data.Mark;

import java.util.HashMap;
import java.util.Map;

public class MarkUtils
{
    public static final Map<Mark, Mark> otherMarkMap = new HashMap<Mark, Mark>()
    {{
            put(Mark.X, Mark.O);
            put(Mark.O, Mark.X);
            put(Mark.EMPTY, Mark.EMPTY);
        }};

    public static Mark getOtherMark(Mark mark)
    {
        return otherMarkMap.get(mark);
    }

}
