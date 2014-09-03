package tictactoe.dropwizard;

import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import tictactoe.GameManager;
import tictactoe.web.TicTacToeResource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

public class TicTacToeApplication extends Application<NoConfiguration>
{
    public static final String CROSS_DOMAIN_ACCESS_HEADER_ORIGIN = "Access-Control-Allow-Origin";
    public static final String CROSS_DOMAIN_ACCESS_HEADER_METHODS = "Access-Control-Allow-Methods";
    private static final String CROSS_DOMAIN_ACCESS_HEADER_HEADERS = "Access-Control-Allow-Headers";

    public static void main(String[] args) throws Exception
    {
        new TicTacToeApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<NoConfiguration> bootstrap)
    {
        bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
    }

    @Override
    public void run(NoConfiguration noConfiguration, Environment environment) throws Exception
    {
        environment.jersey().register(new TicTacToeResource());
        environment.jersey().getResourceConfig().getSingletons().add(new SingletonTypeInjectableProvider<Context, GameManager>(GameManager.class, GameManager.newGameManager())
        {
        });

        addHttpHeaders(environment.jersey().getResourceConfig());
        environment.jersey().setUrlPattern("/api/*");
    }

    private static void addHttpHeaders(ResourceConfig resourceConfig)
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
