package ru.mremne;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:13.
 */
public class Server extends ResourceConfig {
    public Server(){
        register(FreemarkerMvcFeature.class);
        register(JacksonFeature.class);
        packages(Server.class.getPackage().getName());
    }

}
