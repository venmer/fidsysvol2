package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.mongo.dao.User;
import ru.mremne.service.MongoService;
import ru.mremne.view.ViewUserData;

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
    @Template(name = "/templates/users.ftl")
    public ViewUserData showProducts() {
        ViewUserData viewData=new ViewUserData();
        viewData.setUsers(mongoService.getAllUsers().asList());
        return viewData;
    }

}
