package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.mongo.dao.User;
import ru.mremne.service.MongoService;
import ru.mremne.view.ViewUserData;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 23:12.
 */

@Path("/auth")
public class AuthResource {
    private static final Logger LOG = Logger.getLogger(AuthResource.class);
    @Inject
    private MongoService mongoService;
    final static String USER_ID_ATTRIBUTE = "userId";

    @Context
    SecurityContext securityContext;

    @Context
    HttpServletRequest request;

    @Context
    HttpServletResponse response;

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addUser(@FormParam("login") String login,
                          @FormParam("email") String email,
                          @FormParam("name") String name,
                          @FormParam("password") String password) throws IOException {
        if (mongoService.getUserByLogin(login) != null) {
            response.sendRedirect("/auth/register/error");
            return "";
        }
        User user = new User();
        user.setEmail(email);
        user.setLogin(login);
        user.setName(name);
        user.setPassword(password);
        mongoService.saveUser(user);
        response.sendRedirect("/");
        return "ok";

    }

    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String processLogin(@FormParam("signin-login") String login,
                               @FormParam("signin-pass") String hash) throws IOException {

        User user = mongoService.getUserByLoginAndPass(login, hash);
        if (user == null) {
            System.out.println("processLogin | name or password is wrong");
            response.sendRedirect("/auth/signin/error");
            return "not found";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(USER_ID_ATTRIBUTE, user.getId());

        response.sendRedirect(resolveRedirectUrl());
        return "ok!";
    }

    private String resolveRedirectUrl() {
        String referer = request.getHeader("referer");
        if (referer == null || referer.isEmpty()) {
            return "/";
        }
        try {
            java.net.URI uri = new java.net.URI(referer);
            if (!uri.isAbsolute() && uri.getHost() == null
                    && referer.startsWith("/") && !referer.startsWith("//")) {
                return referer;
            }
            String host = uri.getHost();
            if (host != null && host.equalsIgnoreCase(request.getServerName())) {
                return uri.getPath() != null && !uri.getPath().isEmpty() ? uri.getPath() : "/";
            }
        } catch (Exception e) {
            LOG.warn("invalid referer: " + referer);
        }
        return "/";
    }

    @GET
    @Path("/signin/error")
    @Template(name = "/templates/login/error.ftl")
    public ViewUserData showLoginError() throws IOException {
        return new ViewUserData();
    }

    @GET
    @Path("/signout")
    public String processLogout() throws IOException {
        HttpSession session = request.getSession(true);
        session.removeAttribute(USER_ID_ATTRIBUTE);
        response.sendRedirect("/");
        return "";
    }

    @GET
    @Path("/register/error")
    @Template(name = "/templates/register/exist.ftl")
    public ViewUserData showRegisterError() {
        return new ViewUserData();
    }

}
