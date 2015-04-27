package ru.mremne.view;

import ru.mremne.model.mongo.dao.User;

import java.util.List;

/**
 * autor:maksim
 * date: 28.04.15
 * time: 0:36.
 */
public class ViewUserData {
    private String name;
    private String surname;
    private String email;
    private List<User> users;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
