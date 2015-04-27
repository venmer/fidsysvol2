package ru.mremne.service;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import ru.mremne.model.mongo.dao.Product;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;

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
    public Query<Product> getAllProducts() {
        Query<Product> products=getProductDAO().getDs().find(Product.class);
        return products;
    }
}
