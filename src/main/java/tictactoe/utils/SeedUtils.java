package tictactoe.utils;

import tictactoe.data.Seed;

import java.util.HashMap;
import java.util.Map;

public class SeedUtils
{
    public static final Map<Seed, Seed> otherPlayerMap = new HashMap<Seed, Seed>()
    {{
            put(Seed.COMPUTER, Seed.OPPONENT);
            put(Seed.OPPONENT, Seed.COMPUTER);
            put(Seed.EMPTY, Seed.EMPTY);
        }};

    public static Seed getOtherPlayer(Seed seed)
    {
        return otherPlayerMap.get(seed);
    }

}
