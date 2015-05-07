package ru.mremne.service;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Morphia;
import ru.mremne.config.ServerConfig;
import ru.mremne.model.mongo.dao.Product;

import java.net.UnknownHostException;

/**
 * autor:maksim
 * date: 23.04.15
 * time: 12:55.
 */
public class MongoProvider {
    private static final Logger LOG =Logger.getLogger(MongoProvider.class);
    private static Morphia morphia = null;
    private static MongoClient mongo = null;
    private static String database = "";
    private static ServerConfig serverConfig=ServerConfig.newInstance();

    public static Morphia getMorphia()
    {
        if (morphia == null)
        {
            morphia = new Morphia();
            morphia.mapPackage(Product.class.getPackage().toString());
        }
        return morphia;
    }

    public static MongoClient getMongo() throws UnknownHostException
    {
        if (mongo == null)
        {
            try {
                mongo = new MongoClient(new MongoClientURI(System.getenv("MONGOLAB_URI")));
                LOG.info("Mongo on Heroku");
            } catch (Exception e) {
                LOG.info("Mongo on localhost");
                mongo=new MongoClient(serverConfig.getMongoHost(),serverConfig.getMongoPort());
            }
        }
        return mongo;
    }

    public static String getDB()
    {
        if (database.equals(""))
        {
            try {
                database = (new MongoClientURI(System.getenv("MONGOLAB_URI"))).getDatabase();
                LOG.info("MongoDB on Heroku");
            } catch (Exception e) {
                LOG.info("MongoDB on localhost");
                database=serverConfig.getMongoDataBase();
            }
        }
        return database;
    }
}
