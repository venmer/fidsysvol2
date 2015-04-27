package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import ru.mremne.service.MongoService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@ManagedBean
@Path("/")
@ErrorTemplate(name="/error.ftl")
public class IndexResources {
    @Inject
    private MongoService mongoService;



}
