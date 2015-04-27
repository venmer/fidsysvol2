package ru.mremne.view;

import ru.mremne.model.mongo.dao.Product;

import java.util.List;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 17:06.
 */
public class ViewData {
    public Product singleProduct;
    public List<Product> allProducts;

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
}
