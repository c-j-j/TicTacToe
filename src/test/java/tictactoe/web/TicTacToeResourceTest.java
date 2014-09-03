package tictactoe.web;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.simple.container.SimpleServerFactory;
import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;
import org.hamcrest.Matchers;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.concurrent.Synchroniser;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import tictactoe.GameManager;
import tictactoe.builders.BoardBuilder;
import tictactoe.builders.BoardFactory;
import tictactoe.data.Board;
import tictactoe.data.GameProgress;
import tictactoe.data.GameState;
import tictactoe.data.Position;
import tictactoe.data.Seed;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.io.Closeable;
import java.io.IOException;

public class TicTacToeResourceTest
{
    @Rule
    public JUnitRuleMockery mockery = new JUnitRuleMockery()
    {{
            setImposteriser(ClassImposteriser.INSTANCE);
            setThreadingPolicy(new Synchroniser());
        }};

    private GameManager gameManager;
    private Closeable server;
    private WebResource webResource;

    @Before
    public void setUp() throws Exception
    {
        gameManager = mockery.mock(GameManager.class);

        Client client = Client.create(new DefaultClientConfig());
        String serverAddress = "http://localhost:5555";
        webResource = client.resource(UriBuilder.fromUri(serverAddress).build());

        DefaultResourceConfig resourceConfig = new DefaultResourceConfig(TicTacToeResource.class);
        configureSingleton(resourceConfig, gameManager, GameManager.class);

        server = SimpleServerFactory.create(serverAddress, resourceConfig);

    }

    @After
    public void shutdownJersey() throws IOException
    {
        server.close();
    }


    private <T> void configureSingleton(DefaultResourceConfig resourceConfig, T singleton, Class<T> singletonType)
    {
        resourceConfig.getSingletons().add(new SingletonTypeInjectableProvider<Context, T>(singletonType, singleton)
        {
        });
    }


    @Test
    public void shouldStartNewGameWithComputerPlayingFirstGo() throws Exception
    {
        testNewGameWebService(Seed.COMPUTER);
    }

    @Test
    public void shouldStartNewGameWithOpponentPlayingFirstGo() throws Exception
    {
        testNewGameWebService(Seed.OPPONENT);
    }

    @Test
    public void shouldPlayNextMoveGivenBoardAndNextPosition() throws Exception
    {
        Board currentBoard = new BoardBuilder().withMove(Position.BOTTOM_CENTRE, Seed.COMPUTER).build();
        Position opponentPositionToBePlayed = Position.CENTRE;

        Board boardAfterOpponentHasPlayed = BoardFactory.addMove(currentBoard, opponentPositionToBePlayed, Seed.OPPONENT);
        GameProgress simulatedGameProgress = new GameProgress(GameState.COMPUTER_LOSES,
                BoardFactory.addMove(boardAfterOpponentHasPlayed, Position.TOP_LEFT, Seed.COMPUTER));
        expectGameManagerPlayMethodToBeCalled(boardAfterOpponentHasPlayed, simulatedGameProgress);

        ClientResponse clientResponse = invokeWebServiceToPlayNextMove(currentBoard, opponentPositionToBePlayed);

        GameProgress gameProgress = GameProgress.inflateFromJson(clientResponse.getEntity(String.class));

        Assert.assertThat(gameProgress, Matchers.is(simulatedGameProgress));
    }

    private void expectGameManagerPlayMethodToBeCalled(final Board boardAfterOpponentHasPlayed, final GameProgress simulatedGameProgress)
    {
        mockery.checking(new Expectations()
        {{
                oneOf(gameManager).play(with(boardAfterOpponentHasPlayed));
                will(returnValue(simulatedGameProgress));
            }});
    }

    private ClientResponse invokeWebServiceToPlayNextMove(Board currentBoard, Position opponentPositionToBePlayed)
    {
        return webResource.path(TicTacToeResource.ROOT)
                .path(TicTacToeResource.NEXT_MOVE)
                .queryParam(TicTacToeResource.NEXT_POSITION, opponentPositionToBePlayed.name())
                .queryParam(TicTacToeResource.BOARD, currentBoard.toJson())
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
    }

    private void testNewGameWebService(Seed seed)
    {
        GameProgress simulatedGameProgress = expectCallToGameManagerStart(seed);
        ClientResponse clientResponse = callNewGameWebResource(seed);
        GameProgress gameProgress = GameProgress.inflateFromJson(clientResponse.getEntity(String.class));
        Assert.assertThat(gameProgress.getBoard(), Matchers.is(simulatedGameProgress.getBoard()));
    }

    private GameProgress expectCallToGameManagerStart(Seed seed)
    {
        GameProgress gameProgress = new GameProgress(GameState.IN_PROGRESS, BoardFactory.emptyBoard());

        mockery.checking(new Expectations()
        {{
                oneOf(gameManager).start(seed);
                will(returnValue(gameProgress));

            }});

        return gameProgress;
    }

    private ClientResponse callNewGameWebResource(Seed seed)
    {
        return webResource.path(TicTacToeResource.ROOT)
                .path(TicTacToeResource.NEW_GAME)
                .queryParam(TicTacToeResource.FIRST_PLAYER, seed.name())
                .accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
    }
}