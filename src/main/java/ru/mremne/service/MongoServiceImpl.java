package ru.mremne.service;

import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Criteria;
import org.mongodb.morphia.query.Query;
import ru.mremne.model.mongo.dao.Product;
import ru.mremne.model.mongo.dao.User;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;

import static ru.mremne.model.mongo.dao.ProductDAO.getProductDAO;
import static ru.mremne.model.mongo.dao.UserDAO.getUserDAO;

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

    @Override
    public Query<User> getAllUsers() {
        Query<User> users=getUserDAO().getDs().find(User.class);
        return users;
    }

    @Override
    public void saveUser(User user) {
        getUserDAO().save(user);

    }

    @Override
    public User getUser(String login,String pass) {
        Query query= getUserDAO().createQuery();
        Criteria forLogin= (Criteria) query.criteria("login").equal(login);
        Criteria forPass= (Criteria) query.criteria("password").equal(pass.hashCode());
        query.and(forLogin,forPass);
        System.out.println("query: " + pass);
        User user=getUserDAO().findOne(query);
        return user;
    }

    @Override
    public User getUserById(String id) {
        User user=getUserDAO().findOne("_id",id);
        return user;
    }
}
