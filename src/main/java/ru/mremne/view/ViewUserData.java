package ru.mremne.view;

import ru.mremne.model.mongo.dao.User;
import ru.mremne.model.mongo.dao.identification.Result;

import java.util.List;

/**
 * autor:maksim
 * date: 28.04.15
 * time: 0:36.
 */
public class ViewUserData {
    public User authUser;
    public List<Result> results;
    public User profile;

    public User getAuthUser() {
        return authUser;
    }

    public void setAuthUser(User authUser) {
        this.authUser = authUser;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public User getProfile() {
        return profile;
    }

    public void setProfile(User profile) {
        this.profile = profile;
    }
}
