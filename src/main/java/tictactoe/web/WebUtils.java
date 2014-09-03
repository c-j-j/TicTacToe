package tictactoe.web;

import com.sun.jersey.api.core.DefaultResourceConfig;
import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;

import javax.ws.rs.core.Context;

public class WebUtils
{
    public static <T> void configureSingleton(DefaultResourceConfig resourceConfig, T singleton, Class<T> singletonType)
    {
        resourceConfig.getSingletons().add(new SingletonTypeInjectableProvider<Context, T>(singletonType, singleton)
        {
        });
    }

}
