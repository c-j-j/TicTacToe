package tictactoe.data;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

public class PositionNumberingTest
{
    @Test
    public void topLeftCorrespondsTo0()
    {
        testPositionCorrespondsToInteger(Position.TOP_LEFT, 0);
    }

    @Test
    public void topCentreCorrespondsTo1()
    {
        testPositionCorrespondsToInteger(Position.TOP_CENTRE, 1);
    }

    @Test
    public void topRightCorrespondsTo2()
    {
        testPositionCorrespondsToInteger(Position.TOP_RIGHT, 2);
    }

    @Test
    public void middleLeftCorrespondsTo3()
    {
        testPositionCorrespondsToInteger(Position.MIDDLE_LEFT, 3);
    }

    @Test
    public void centreCorrespondsTo4()
    {
        testPositionCorrespondsToInteger(Position.CENTRE, 4);
    }

    @Test
    public void middleRightCorrespondsTo5()
    {
        testPositionCorrespondsToInteger(Position.MIDDLE_RIGHT, 5);
    }

    @Test
    public void bottomLeftCorrespondsTo6()
    {
        testPositionCorrespondsToInteger(Position.BOTTOM_LEFT, 6);
    }


    @Test
    public void bottomCentreCorrespondsTo7()
    {
        testPositionCorrespondsToInteger(Position.BOTTOM_CENTRE, 7);
    }

    @Test
    public void bottomRightCorrespondsTo8()
    {
        testPositionCorrespondsToInteger(Position.BOTTOM_RIGHT, 8);
    }


    private void testPositionCorrespondsToInteger(Position position, int integerRepresentationOfPosition)
    {
        Assert.assertThat(position.getIntegerRepresentation(), Matchers.is(integerRepresentationOfPosition));
        Assert.assertThat(Position.getPosition(integerRepresentationOfPosition), Matchers.is(position));
    }
}