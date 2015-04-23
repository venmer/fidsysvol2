package ru.mremne.model.mongo.dao;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import ru.mremne.model.mongo.beans.Product;
import ru.mremne.service.MongoProvider;

import java.net.UnknownHostException;

/**
 * autor:maksim
 * date: 23.04.15
 * time: 15:21.
 */
public class ProductDAO extends BasicDAO<Product,ObjectId> {
   public ProductDAO(MongoClient mongo,Morphia morphia, String dbName){
       super(mongo,morphia,dbName);
   }
   public static ProductDAO getProductDAO(){
       try {
           return new ProductDAO(MongoProvider.getMongo(), MongoProvider.getMorphia(), MongoProvider.getDB());
       } catch (UnknownHostException e) {
           e.printStackTrace();
           return null;
       }
   }

}
