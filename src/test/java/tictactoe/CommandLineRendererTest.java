package tictactoe;

import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Mark;
import tictactoe.data.Position;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

public class CommandLineRendererTest
{
    @Test
    public void getInputFromUser()
    {
        Integer simulatedInput = 0;
        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.toString().getBytes());
        System.setIn(in);

        Position position = new CommandLineRenderer(in).getPositionFromUser(new BoardBuilder().build(), Mark.O);

        Assert.assertThat(position.getIntegerRepresentation(), Matchers.is(simulatedInput));
    }


}