package ru.mremne.model.mongo.dao;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.security.Principal;
import java.util.UUID;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 22:33.
 */
@Entity
public class User implements Principal {
    @Id
    private String id;
    private String login;
    private String name;
    private String email;
    private int password;

    public User(){
        this.id= UUID.randomUUID().toString();
    }
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

    public String getId() {
        return id;
    }


    public void setPassword(int password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
       this.password=password.hashCode();
    }

    @Override
    public String toString() {
        return "name: "+name+" email"+email;
    }
}
