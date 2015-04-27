package ru.mremne.model.mongo.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * autor:maksim
 * date: 22.04.15
 * time: 22:19.
 */
@Entity
public class Product {

    @Id public ObjectId id;
    public String type;
    public String description;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "id: "+id+"type: "+type+"description"+description;
    }
}
