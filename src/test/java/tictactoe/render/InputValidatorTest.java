package tictactoe.render;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.data.Position;

public class InputValidatorTest
{
    @Test
    public void shouldNotBeValidIfNumberIsNotGreaterThan0()
    {
        Assert.assertFalse(InputValidator.isValid(InputValidator.MIN_INPUT_ALLOWED - 1, null));
    }

    @Test
    public void shouldNotBeValidIfNumberIsNotGreaterThan8()
    {
        Assert.assertFalse(InputValidator.isValid(InputValidator.MAX_INPUT_ALLOWED+1, null));
    }

    @Test
    public void shouldNotBeValidIfNumberProvidedDoesNotCorrespondToEmptyPosition()
    {
        Assert.assertFalse(InputValidator.isValid(Position.TOP_CENTRE.getIntegerRepresentation(), Lists.newArrayList(Position.BOTTOM_CENTRE)));
    }

    @Test
    public void shouldBeValidIfInputIsInRangeAndIsAnEmptyPosition()
    {
        Assert.assertTrue(InputValidator.isValid(Position.TOP_CENTRE.getIntegerRepresentation(), Lists.newArrayList(Position.TOP_CENTRE)));
    }
}