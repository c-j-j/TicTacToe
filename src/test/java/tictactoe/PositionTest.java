package tictactoe;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.data.Position;

public class PositionTest
{
    @Test
    public void testTopLeftPosition() throws Exception
    {
        Assert.assertThat(Position.TOP_LEFT.getArrayPosition(), Matchers.is(0));
    }

    @Test
    public void testTopCentrePosition() throws Exception
    {
        Assert.assertThat(Position.TOP_CENTRE.getArrayPosition(), Matchers.is(1));
    }

    @Test
    public void testTopRightPosition() throws Exception
    {
        Assert.assertThat(Position.TOP_RIGHT.getArrayPosition(), Matchers.is(2));
    }

    @Test
    public void testMiddleLeftPosition() throws Exception
    {
        Assert.assertThat(Position.MIDDLE_LEFT.getArrayPosition(), Matchers.is(3));
    }

    @Test
    public void testCentrePosition() throws Exception
    {
        Assert.assertThat(Position.CENTRE.getArrayPosition(), Matchers.is(4));
    }

    @Test
    public void testMiddleRightPosition() throws Exception
    {
        Assert.assertThat(Position.MIDDLE_RIGHT.getArrayPosition(), Matchers.is(5));
    }

    @Test
    public void testBottomLeftPosition() throws Exception
    {
        Assert.assertThat(Position.BOTTOM_LEFT.getArrayPosition(), Matchers.is(6));
    }

    @Test
    public void testBottomCentrePosition() throws Exception
    {
        Assert.assertThat(Position.BOTTOM_CENTRE.getArrayPosition(), Matchers.is(7));
    }

    @Test
    public void testBottomRightPosition() throws Exception
    {
        Assert.assertThat(Position.BOTTOM_RIGHT.getArrayPosition(), Matchers.is(8));
    }
}