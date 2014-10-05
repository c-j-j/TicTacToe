package tictactoe.render;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardBuilder;
import tictactoe.data.Mark;
import tictactoe.data.Position;
import tictactoe.render.CommandLineRenderer;

import java.io.ByteArrayInputStream;

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