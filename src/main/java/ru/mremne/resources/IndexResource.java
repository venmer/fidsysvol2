package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.service.MongoService;
import ru.mremne.view.ViewResultData;
import ru.mremne.view.ViewUserData;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@Path("/")
@Produces("text/html")
public class IndexResource {
    @Inject
    private MongoService mongoService;
    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;

    @Context
    SecurityContext securityContext;
    @GET
    @Template(name = "/templates/welcome.ftl")
    public ViewUserData welcome(){
        ViewUserData viewUserData=new ViewUserData();
        viewUserData.authUser= (ru.mremne.model.mongo.dao.User) securityContext.getUserPrincipal();
        System.out.println("auth user "+securityContext.getUserPrincipal());
        viewUserData.results= (java.util.List<ru.mremne.model.mongo.dao.identification.Result>) mongoService.getAllResults().asList();
        return viewUserData;
    }
    @GET
    @Path("/results")
    @Template(name = "/templates/results.ftl")
    public ViewResultData showProducts() {
        ViewResultData viewData=new ViewResultData();
        viewData.setResults(mongoService.getAllResults().asList());
        System.out.println("size of resultsList: "+viewData.getResults().size());
        return viewData;
    }
}
