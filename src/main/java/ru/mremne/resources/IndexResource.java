package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.mongo.dao.User;
import ru.mremne.model.mongo.dao.identification.Result;
import ru.mremne.view.ViewUserData;

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
    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;

    @Context
    SecurityContext securityContext;

    @GET
    @Template(name = "/templates/welcome.ftl")
    public ViewUserData welcome() {
        return getCurrentUserData();
    }

    @GET
    @Path("/results")
    @Template(name = "/templates/results.ftl")
    public ViewUserData showProducts() {
        return getCurrentUserData();
    }

    @GET
    @Path("/profile")
    @Template(name = "/templates/profile/profile.ftl")
    public ViewUserData userProfile() {
        ViewUserData viewData = getCurrentUserData();
        for (Result r : ((User) securityContext.getUserPrincipal()).getResults())
            System.out.println(r.toString());
        return viewData;
    }

    private ViewUserData getCurrentUserData() {
        ViewUserData viewUserData = new ViewUserData();
        viewUserData.authUser = (ru.mremne.model.mongo.dao.User) securityContext.getUserPrincipal();
        System.out.println("auth user " + securityContext.getUserPrincipal());
        return viewUserData;
    }

}
