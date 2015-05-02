package ru.mremne.auth;

import ru.mremne.model.mongo.dao.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * autor:maksim
 * date: 30.04.15
 * time: 14:14.
 */
public class AuthUser implements SecurityContext {
    private User user;
    public AuthUser(final User user){
        this.user=user;
    }
    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public boolean isUserInRole(String role) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
