package ru.mremne.resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;
import org.glassfish.jersey.server.mvc.ErrorTemplate;
import ru.mremne.service.FidService;
import ru.mremne.util.Util;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ErrorTemplate(name="/error.ftl")
public class IndexResources {
    private static final Logger log=Logger.getLogger(IndexResources.class);
    final FidService service = new FidService(Util.getNeo4jUrl());
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    @GET
    @Path("/identifier/status/{id} ")
    public String status(@PathParam("id") String id ){
        log.info("in status: "+id);
        return "status";
    }
    @POST
    @Path("/identifier/identify_old ")
    public static Response identify_old(){
        log.info("in old");
        return Response.ok().build();
    }

}
