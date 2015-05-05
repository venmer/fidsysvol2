package ru.mremne.service;

import org.apache.log4j.Logger;
import org.neo4j.helpers.collection.IteratorUtil;
import ru.mremne.config.ServerConfig;
import ru.mremne.executor.CypherExecutor;
import ru.mremne.executor.JdbcCypherExecutor;
import ru.mremne.model.identification.Labels;
import ru.mremne.model.identification.Relationships;
import ru.mremne.model.identification.Result;
import ru.mremne.model.identification.ResultPoints;

import javax.annotation.ManagedBean;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.neo4j.helpers.collection.MapUtil.map;

/**
 * @author maksim
 * date: 20.12.14
 * time: 21:30.
 */
@Resource
@ManagedBean
public class FidServiceImpl implements FidService {
    private final CypherExecutor cypher=createCypherExecutor();
    private static final Logger LOG =Logger.getLogger(FidServiceImpl.class);
    public static final int CONSTR=5;
    private CypherExecutor createCypherExecutor(){
    ServerConfig serverConfig=ServerConfig.newInstance();
        return new JdbcCypherExecutor(serverConfig.getNeo4jHost(),serverConfig.getNeo4jPort());
    }

    @Override
    public boolean addAngles(double[] angles){
       LOG.info("add angles..");
        if(angles.length!=0){
            int level=0;
            for(int i=0;i<angles.length-1;i++){
                cypher.query("MATCH (n:"+ Labels.INTERVALS +"{value: "+angles[i]+" })," +
                        "(m:"+Labels.INTERVALS +"{value: "+angles[i+1]+"}) " +
                        "CREATE UNIQUE (n)-[:"+ Relationships.LEVEL+"{level: "+level+" }]->(m) " +
                        "return m",map("1",null));
                level++;
            }
          return true;
        }else{
            LOG.error("nothing to add!");
            return false;
        }
    }
    @Override
    public boolean checkAngles(double[] angles){
        int levelExpected=angles.length-1;
        int levelActual=0;
        LOG.info("checking angles...");
        SortedSet<String> identityList=new TreeSet<>();
        if(angles.length!=0){
            for(int i=0;i<angles.length-1;i++){
                identityList.add(IteratorUtil.asCollection(cypher.query("START n = node(*)\n" +
                        "MATCH n-[r:"+Relationships.LEVEL+"]->c\n" +
                        "WHERE HAS(n.value) AND HAS(c.value) " +
                        "AND n.value>(" + (angles[i] - CONSTR) + ") AND n.value<(" + (angles[i] + CONSTR) + ")\n" +
                        "AND c.value>(" + (angles[i + 1] - CONSTR) + ") AND c.value<(" + (angles[i + 1] + CONSTR) + ")\n" +
                        "RETURN n, r,c", map("1", i))).toString());
            }
            LOG.info("map.size =" + identityList.size());
            for(String m:identityList){
                int tmp=ResultPoints.extractMaxLevel(m);
                if(tmp>levelActual) {
                    LOG.info("founded angles: " + m);
                    levelActual=tmp;
                }
            }
        }else{
            LOG.error("nothing to search!!");
            return false;
        }
        LOG.info("expected level was : " + levelExpected + " ,but actual is : " + levelActual);
        if(Math.abs(levelExpected-levelActual)<=levelActual) {
            LOG.info("everything is ok!!");
            return true;
        }
        return false;
    }
    @Override
    public void saveStatus(Result result){
        LOG.info("Saving status info...");
        if(result!=null) {
            cypher.query("CREATE (r: " + Labels.STATUS + "{" + result.toString() + "}) return r", map("1", null));
            LOG.info("ok!");
        }else{
            LOG.warn("no results to save!");
        }
    }
    @Override
    public Map<String,Object> getStatus(String id){
        LOG.info("Get results by id..");
        Map<String, Object> params = new HashMap<>();
        if(id!=null){
             params=cypher.query("MATCH (r: "+Labels.STATUS +"{id: \""+id+"\"}) return r.id AS id, r.status AS status, r.result AS result  "
                                ,map("1",null)).next();
            LOG.info("ok");
        }
        return params;
    }

    public void deleteCurrentStatus(String id){
        cypher.query("MATCH (r: "+Labels.STATUS +"{id: \""+id+"\"}) DELETE r",map(null,null));
    }
}
