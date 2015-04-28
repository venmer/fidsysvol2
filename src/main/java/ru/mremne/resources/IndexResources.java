package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@Path("/")
public class IndexResources {
    @GET
    @Template(name = "/templates/welcome.ftl")
    public String welcomePage() {
        return "welcome";
    }
    @GET
    @Path("/try")
    @Template(name="/templates/start.ftl")
    public String tryIt(){
        return "try it";
    }


}
