package ru.mremne.service;

import org.apache.log4j.Logger;
import org.jvnet.hk2.annotations.Service;
import org.neo4j.helpers.collection.IteratorUtil;
import ru.mremne.config.ServerConfig;
import ru.mremne.executor.CypherExecutor;
import ru.mremne.executor.JdbcCypherExecutor;
import ru.mremne.model.identification.Labels;
import ru.mremne.model.identification.Matching;
import ru.mremne.model.identification.Relationships;

import javax.annotation.ManagedBean;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.neo4j.helpers.collection.MapUtil.map;

/**
 * @author maksim
 * date: 20.12.14
 * time: 21:30.
 */
@Service
@ManagedBean
public class FidServiceImpl implements FidService {
    private final CypherExecutor cypher=createCypherExecutor();
    private static final Logger LOG =Logger.getLogger(FidServiceImpl.class);
    public static final double CONSTR=1;             // angles error +-1 grad
    private CypherExecutor createCypherExecutor(){
    ServerConfig serverConfig=ServerConfig.newInstance();
        return new JdbcCypherExecutor(serverConfig.getNeo4jHost(),serverConfig.getNeo4jPort());
    }

    @Override
    public synchronized boolean addAngles(Double[] angles){
       LOG.info("add angles..");
        if(angles.length!=0){
            int level=0;
            for(int i=0;i<angles.length-1;i++){
                cypher.query("MATCH (n:"+ Labels.INTERVALS +"{value: "+angles[i]+" })," +
                        "(m:"+Labels.INTERVALS +"{value: "+angles[i+1]+"}) " +
                        "CREATE UNIQUE (n)-[:"+ Relationships.LEVEL+"{level: "+level+" }]->(m) " +
                        "",map("1",null));
                level++;
            }
            LOG.info("all angles is checked");
          return true;
        }else{
            LOG.error("nothing to add!");
            return false;
        }
    }
    @Override
    public synchronized Matching checkAngles(Double[] angles) {
        int levelExpected=angles.length-1;
        int levelActual=0;
        int tmpLevel=0;
        LOG.info("checking angles...");
        List<Integer> identityList=new ArrayList<>();
        String identyString;
        if(angles.length!=0){
            for(int i=0;i<angles.length-1;i++){
                identyString=(IteratorUtil.asCollection(cypher.query("START n = node(*)\n" +
                        "MATCH n-[r:"+Relationships.LEVEL+"{level:"+tmpLevel+"}]->c\n" +
                        "WHERE HAS(n.value) AND HAS(c.value) " +
                        "AND n.value>(" + (angles[i] - CONSTR) + ") AND n.value<(" + (angles[i] + CONSTR) + ")\n" +
                        "AND c.value>(" + (angles[i + 1] - CONSTR) + ") AND c.value<(" + (angles[i + 1] + CONSTR) + ")\n" +
                        "RETURN n, r,c", map("1", i))).toString());
                LOG.info("identy"+identyString);
                if(!identyString.equals("[]")){
                     levelActual++;
                }else{
                    identityList.add(levelActual);
                    levelActual=0;
                }
                tmpLevel++;
            }
            LOG.info("map.size =" + identityList.size());
            for(Integer i:identityList){
                LOG.info("levels: "+    i.toString());
            }
        }else{
            LOG.error("nothing to search!!");
            return new Matching(0, 0);
        }
        LOG.info("max: "+Collections.max(identityList));
        levelActual=Collections.max(identityList);
        LOG.info("expected level was : " + levelExpected + " ,but actual is : " + levelActual);
        if(Math.abs(levelExpected-levelActual)<=levelActual) {
            LOG.info("everything is ok!!");
        }
        LOG.info("match level ex: " + levelExpected);
        LOG.info("match level act: " + levelActual);
        return new Matching(levelExpected, levelActual);
    }
}
