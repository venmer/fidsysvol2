package ru.mremne.view;

import ru.mremne.model.mongo.dao.Product;
import ru.mremne.model.mongo.dao.User;

import java.util.List;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 17:06.
 */
public class ViewData {
    private Product singleProduct;
    private List<Product> allProducts;
    private User user;
    private List<User> users;
    public Product getSingleProduct() {
        return singleProduct;
    }

    public void setSingleProduct(Product singleProduct) {
        this.singleProduct = singleProduct;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
