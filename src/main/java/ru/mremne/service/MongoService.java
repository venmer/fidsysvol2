package ru.mremne.service;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import ru.mremne.model.mongo.dao.Product;
import ru.mremne.model.mongo.dao.User;

/**
 * autor:maksim
 * date: 23.04.15
 * time: 14:52.
 */
public interface MongoService {
    public void addProduct(Product product);
    public Product getProductById(ObjectId id);
    public Query<Product> getAllProducts();
    public void saveUser(User user);
    public User getUser(String name, String pass);
    public User getUserByLogin(String login);
    public User getUserById(String id);
    public Query<User> getAllUsers();
}
