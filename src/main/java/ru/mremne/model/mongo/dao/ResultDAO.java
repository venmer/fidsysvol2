package ru.mremne.model.mongo.dao;

import com.mongodb.MongoClient;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import ru.mremne.model.mongo.dao.identification.Result;
import ru.mremne.service.MongoProvider;

import java.net.UnknownHostException;

/**
 * autor:maksim
 * date: 07.05.15
 * time: 23:05.
 */
public class ResultDAO extends BasicDAO<Result,ObjectId> {
    public ResultDAO(MongoClient mongo,Morphia morphia, String dbName){
        super(mongo,morphia,dbName);
    }
    public static ResultDAO getResultDAO(){
        try {
            return new ResultDAO(MongoProvider.getMongo(), MongoProvider.getMorphia(), MongoProvider.getDB());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return null;
        }
    }
}
