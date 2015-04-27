package ru.mremne.model.mongo.dao;

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import ru.mremne.service.MongoProvider;

import java.net.UnknownHostException;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 22:34.
 */
public class UserDAO extends BasicDAO<User,ObjectId> {
    private static Logger LOG=Logger.getLogger(UserDAO.class);
    public UserDAO(MongoClient mongo,Morphia morphia, String dbname){
        super(mongo,morphia,dbname);
    }
    public static UserDAO getUserDAO(){

        try {
            return new UserDAO(MongoProvider.getMongo(),MongoProvider.getMorphia(),MongoProvider.getDB());
        } catch (UnknownHostException e) {
            LOG.error("mongo unknown host");
            return null;
        }
    }

}
