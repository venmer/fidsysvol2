package ru.mremne.service;

import org.bson.types.ObjectId;
import ru.mremne.model.mongo.beans.Product;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import java.util.Map;

import static ru.mremne.model.mongo.dao.ProductDAO.getProductDAO;

/**
 * autor:maksim
 * date: 23.04.15
 * time: 14:54.
 */
@Resource
@ManagedBean
public class MongoServiceImpl implements MongoService {
    @Override
    public void addProduct(Product product) {
       getProductDAO().save(product);

    }

    @Override
    public Product getProductById(ObjectId id) {
        return null;
    }

    @Override
    public Map<ObjectId, Product> getAllProducts() {
        return null;
    }
}
