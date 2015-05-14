package ru.mremne.model.mongo.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 22:33.
 */
@Entity
public class User {
    @Id
    private ObjectId objectId;
    private String login;
    private String name;
    private String email;
    private int password;

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

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
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
