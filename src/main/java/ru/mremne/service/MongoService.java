package ru.mremne.service;

import org.bson.types.ObjectId;
import ru.mremne.model.mongo.beans.Product;

import java.util.Map;

/**
 * autor:maksim
 * date: 23.04.15
 * time: 14:52.
 */
public interface MongoService {
    public void addProduct(Product product);
    public Product getProductById(ObjectId id);
    public Map<ObjectId,Product> getAllProducts();
}
