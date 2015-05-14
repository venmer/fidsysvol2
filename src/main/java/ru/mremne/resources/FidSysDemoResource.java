package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * autor:maksim
 * date: 11.05.15
 * time: 1:03.
 */
@Path("/try")
@Produces("text/html")
public class FidSysDemoResource {
    @GET
    @Path("/")
    @Template(name="/templates/fidsysdemo/step_one.ftl")
    public String tryIt(){
        return "try it";
    }
    @GET
    @Path("/desc")
    @Template(name="/templates/fidsysdemo/step_two.ftl")
    public String stepTwoPage(){
        System.out.print("int try two page");
        return "ok";
    }
}
