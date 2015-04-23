package ru.mremne.config;

import ru.yandex.qatools.properties.PropertyLoader;
import ru.yandex.qatools.properties.annotations.Property;
import ru.yandex.qatools.properties.annotations.Resource;
import ru.yandex.qatools.properties.annotations.With;
import ru.yandex.qatools.properties.providers.MapOrSyspropPathReplacerProvider;

/**
 * autor:maksim
 * date: 22.04.15
 * time: 17:01.
 */
@With(MapOrSyspropPathReplacerProvider.class)
@Resource.Classpath("${system.environment}.properties")
public class ServerConfig {
    @Property("mongo.host")
    private String mongoHost;
    @Property("mongo.port")
    private Integer mongoPort;
    @Property("mongo.database")
    private String mongoDataBase;
    @Property("neo4j.host")
    private String neo4jHost;
    @Property("neo4j.port")
    private Integer neo4jPort;

    ServerConfig(){
        PropertyLoader.populate(this);
    }

    public String getMongoHost() {
        return mongoHost;
    }

    public void setMongoHost(String mongoHost) {
        this.mongoHost = mongoHost;
    }

    public Integer getMongoPort() {
        return mongoPort;
    }

    public void setMongoPort(Integer mongoPort) {
        this.mongoPort = mongoPort;
    }

    public Integer getNeo4jPort() {
        return neo4jPort;
    }

    public void setNeo4jPort(Integer neo4jPort) {
        this.neo4jPort = neo4jPort;
    }

    public String getNeo4jHost() {
        return neo4jHost;
    }

    public void setNeo4jHost(String neo4jHost) {
        this.neo4jHost = neo4jHost;
    }

    public String getMongoDataBase() {
        return mongoDataBase;
    }

    public void setMongoDataBase(String mongoDataBase) {
        this.mongoDataBase = mongoDataBase;
    }

    public static ServerConfig newInstance(){
        return new ServerConfig();
    }
}
