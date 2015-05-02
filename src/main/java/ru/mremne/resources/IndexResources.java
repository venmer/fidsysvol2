package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    @Template(name="/templates/fidsysdemo/step_one.ftl")
    public String tryIt(){
        return "try it";
    }
    @POST
    @Path("/try/steptwo")
    @Template(name="/templates/fidsysdemo/step_two.ftl")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String stepTwo(String o){
        System.out.println("points: "+o.toString());

        return "step two";
    }


}
