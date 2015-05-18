package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;
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
 * date: 11.05.15
 * time: 1:03.
 */
@Path("/try")
@Produces("text/html")
public class FidSysDemoResource {
    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;

    @Context
    SecurityContext securityContext;

    @GET
    @Path("/")
    @Template(name="/templates/fidsysdemo/step_one.ftl")
    public ViewUserData tryIt(){
        ViewUserData viewUserData=new ViewUserData();
        viewUserData.authUser= (ru.mremne.model.mongo.dao.User) securityContext.getUserPrincipal();
        System.out.println("auth user "+securityContext.getUserPrincipal());
       // viewUserData.results= (java.util.List<ru.mremne.model.mongo.dao.identification.Result>) mongoService.getAllResults().asList();
        return viewUserData;
    }
    @GET
    @Path("/desc")
    @Template(name="/templates/fidsysdemo/step_two.ftl")
    public String stepTwoPage(){
        System.out.print("int try two page");
        return "ok";
    }
}
