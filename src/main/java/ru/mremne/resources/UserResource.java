package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.mongo.dao.User;
import ru.mremne.service.MongoService;
import ru.mremne.view.ViewData;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 23:12.
 */

@Path("/users")
public class UserResource {
    @Inject
    private MongoService mongoService;
    @GET
    @Path("/new")
    @Template(name = "/templates/register.ftl")
    public String registerPage(){
        return "registration";
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addUser(@FormParam("login") String login,
                          @FormParam("email") String email,
                          @FormParam("name") String name,
                          @FormParam("sirname") String sirname,
                          @FormParam("password") String password){
        User user=new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);
        user.setSurname(sirname);
        mongoService.addUser(user);
        return "ok";
    }
    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/templates/products.ftl")
    public ViewData showProducts() {
        ViewData viewData=new ViewData();
        viewData.setAllProducts( mongoService.getAllProducts().asList());
        System.out.println("size of view data: "+viewData.getAllProducts().size());
        return viewData;
    }

}
