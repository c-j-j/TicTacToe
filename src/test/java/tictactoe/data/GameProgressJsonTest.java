package tictactoe.data;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import tictactoe.builders.BoardFactory;

public class GameProgressJsonTest
{
    @Test
    public void shouldInflateGameResultFromJson() throws Exception
    {
        GameProgress gameProgress = new GameProgress(GameState.COMPUTER_LOSES, BoardFactory.emptyBoard());

        String json = gameProgress.toJson();

        GameProgress inflatedGameProgress = GameProgress.inflateFromJson(json);
        Assert.assertThat(inflatedGameProgress, Matchers.is(gameProgress));
        Assert.assertThat(inflatedGameProgress.hashCode(), Matchers.is(gameProgress.hashCode()));
    }

}