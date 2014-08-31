package tictactoe.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CountingMap<T> {
    Map<T, Integer> countingMap = new HashMap<>();

    public void add(T t) {
        countingMap.put(t, getCount(t) + 1);
    }

    public int countOfMostCommonElement() {
        Optional<Integer> max = countingMap.values().stream().max(Comparator.<Integer>naturalOrder());

        if (max.isPresent()) {
            return max.get();
        } else {
            return 0;
        }
    }

    public T mostCommonElement() {
        for (T t : countingMap.keySet()) {
            if (countingMap.get(t) == countOfMostCommonElement()) {
                return t;
            }
        }
        return null;
    }

    public int getCount(T t) {
        if (countingMap.containsKey(t)) {
            return countingMap.get(t);
        } else {
            return 0;
        }
    }
}
