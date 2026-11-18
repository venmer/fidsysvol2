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
import java.util.Collections;

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
                cypher.query("MERGE (n:"+ Labels.INTERVALS +" {value: "+angles[i]+"}) " +
                        "MERGE (m:"+Labels.INTERVALS +" {value: "+angles[i+1]+"}) " +
                        "MERGE (n)-[:"+ Relationships.LEVEL+" {level: "+level+"}]->(m)",
                        Collections.<String, Object>emptyMap());
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
        LOG.info("checking angles...");
        if(angles == null || angles.length <= 1){
            LOG.error("nothing to search!!");
            return new Matching(0, 0);
        }
        int levelExpected=angles.length-1;
        int levelActual=0;
        int currentRun=0;
        String identyString;
        for(int i=0;i<angles.length-1;i++){
            identyString=(IteratorUtil.asCollection(cypher.query("START n = node(*)\n" +
                    "MATCH n-[r:"+Relationships.LEVEL+"{level:"+i+"}]->c\n" +
                    "WHERE HAS(n.value) AND HAS(c.value) " +
                    "AND n.value>(" + (angles[i] - CONSTR) + ") AND n.value<(" + (angles[i] + CONSTR) + ")\n" +
                    "AND c.value>(" + (angles[i + 1] - CONSTR) + ") AND c.value<(" + (angles[i + 1] + CONSTR) + ")\n" +
                    "RETURN n, r,c", Collections.<String, Object>emptyMap())).toString());
            LOG.info("identy"+identyString);
            if(!identyString.equals("[]")){
                currentRun++;
                if(currentRun > levelActual){
                    levelActual = currentRun;
                }
            }else{
                currentRun = 0;
            }
        }
        LOG.info("expected level was : " + levelExpected + " ,but actual is : " + levelActual);
        LOG.info("match level ex: " + levelExpected);
        LOG.info("match level act: " + levelActual);
        return new Matching(levelExpected, levelActual);
    }
}
