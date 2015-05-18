package ru.mremne.service;

import ru.mremne.auth.AuthUser;
import ru.mremne.model.mongo.dao.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import java.io.IOException;

/**
 * autor:maksim
 * date: 18.05.15
 * time: 15:59.
 */
public class AuthUserProvider implements ContainerRequestFilter {
    @Context
    HttpServletRequest request;
    @Inject
    MongoService service;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        HttpSession session = request.getSession(true);
        Object userId = session.getAttribute("userId");
        System.out.println("user id: "+userId);

        User user = null;
        if (userId != null) {
            user = service.getUserById((String) userId);
        }

        requestContext.setSecurityContext(new AuthUser(user));
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
