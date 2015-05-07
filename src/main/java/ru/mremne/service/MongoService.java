package ru.mremne.service;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import ru.mremne.model.mongo.dao.Product;
import ru.mremne.model.mongo.dao.Result;
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
    public void addUser(User user);
    public Query<User> getAllUsers();
    public void saveResult(Result result);
    public Result getResult(String id);
}
