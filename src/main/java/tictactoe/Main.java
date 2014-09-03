package tictactoe;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.simple.container.SimpleServerFactory;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import tictactoe.web.TicTacToeResource;
import tictactoe.web.WebUtils;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;

public class Main
{

    private static final int port = 5555;
    public static final String BASE_URL = "http://localhost:" + port;
    public static final String CROSS_DOMAIN_ACCESS_HEADER_ORIGIN = "Access-Control-Allow-Origin";
    public static final String CROSS_DOMAIN_ACCESS_HEADER_METHODS = "Access-Control-Allow-Methods";
    private static final String CROSS_DOMAIN_ACCESS_HEADER_HEADERS = "Access-Control-Allow-Headers";


    public static void main(String[] args)
    {
        Client client = Client.create(new DefaultClientConfig());
        String serverAddress = BASE_URL;
        client.resource(UriBuilder.fromUri(serverAddress).build());

        DefaultResourceConfig resourceConfig = new DefaultResourceConfig(TicTacToeResource.class);
        WebUtils.configureSingleton(resourceConfig, GameManager.newGameManager(), GameManager.class);

        addHttpHeaders(resourceConfig);

        try
        {
            SimpleServerFactory.create(serverAddress, resourceConfig);
            System.out.println(String.format("TicTacToe web service started on %s", BASE_URL));

        }
        catch (IOException e)
        {
            System.out.println(String.format("Unable to start web service. Check port %d is free", port));
        }
    }

    private static void addHttpHeaders(DefaultResourceConfig resourceConfig)
    {
        //noinspection unchecked,RedundantCast
        resourceConfig.getContainerResponseFilters().add((ContainerResponseFilter) (request, response) -> {
            MultivaluedMap<String,Object> httpHeaders = response.getHttpHeaders();
            httpHeaders.add(CROSS_DOMAIN_ACCESS_HEADER_ORIGIN, "*");
            httpHeaders.add(CROSS_DOMAIN_ACCESS_HEADER_METHODS, "GET, POST, PUT, OPTIONS, DELETE,HEAD");
            httpHeaders.add(CROSS_DOMAIN_ACCESS_HEADER_HEADERS, "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");

            return response;
        });
    }
}
