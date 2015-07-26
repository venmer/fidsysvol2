package ru.mremne;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.freemarker.FreemarkerMvcFeature;
import ru.mremne.service.AuthUserProvider;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:13.
 */
public class Server extends ResourceConfig {
    public Server(){
        register(new ApplicationBinder());
        register(FreemarkerMvcFeature.class);
        register(JacksonFeature.class);
        register(new DynamicFeature() {
            @Override
            public void configure(ResourceInfo resourceInfo, FeatureContext context) {
                context.register(AuthUserProvider.class);
            }
        });
        packages(Server.class.getPackage().getName());
    }

}
