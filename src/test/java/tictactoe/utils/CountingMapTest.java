package tictactoe.utils;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CountingMapTest {

    private CountingMap<String> countingMap;

    @Before
    public void setUp() throws Exception {
        countingMap = new CountingMap<>();
    }

    @Test
    public void shouldAddToCountingMap() {
        String s = "abc";
        countingMap.add(s);

        Assert.assertThat(countingMap.getCount(s), Matchers.is(1));
    }

    @Test
    public void shouldAddTwoToCountingMap() {
        String s = "abc";
        countingMap.add(s);
        countingMap.add(s);

        Assert.assertThat(countingMap.getCount(s), Matchers.is(2));
    }

    @Test
    public void shouldReturnCountOfMostCommonElement() {
        String s = "abc";
        String someOtherString = "someOtherString";
        countingMap.add(s);
        countingMap.add(s);
        countingMap.add(s);
        countingMap.add(someOtherString);
        countingMap.add(someOtherString);

        Assert.assertThat(countingMap.countOfMostCommonElement(), Matchers.is(3));
    }

    @Test
    public void shouldFindMostCommonElement() {
        String s = "abc";
        String someOtherString = "someOtherString";
        countingMap.add(s);
        countingMap.add(s);
        countingMap.add(someOtherString);
        Assert.assertThat(countingMap.mostCommonElement(), Matchers.is(s));
    }

    @Test
    public void shouldReturnNullForCommonElementWhenNoElementExists() {
        Assert.assertThat(countingMap.mostCommonElement(), Matchers.nullValue());
    }

    @Test
    public void shouldReturn0ForMaxCountOfCommonElementGivenAnEmptyCountingMap() {
        Assert.assertThat(countingMap.countOfMostCommonElement(), Matchers.is(0));

    }

}