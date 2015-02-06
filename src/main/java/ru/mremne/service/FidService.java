package ru.mremne.service;

import org.apache.log4j.Logger;
import org.neo4j.helpers.collection.IteratorUtil;
import ru.mremne.executor.CypherExecutor;
import ru.mremne.executor.JdbcCypherExecutor;
import ru.mremne.model.ResultPoints;

import javax.ws.rs.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static org.neo4j.helpers.collection.MapUtil.map;

/**
 * @author maksim
 * date: 20.12.14
 * time: 21:30.
 */
public class FidService {

    private final CypherExecutor cypher;
    private static final Logger log=Logger.getLogger(FidService.class);
    public FidService(String uri) {
        cypher = createCypherExecutor(uri);
    }
    public static final int CONSTR=3;

    private CypherExecutor createCypherExecutor(String uri) {
        try {
            String auth = new URL(uri).getUserInfo();
            if (auth != null) {
                String[] parts = auth.split(":");
                return new JdbcCypherExecutor(uri,parts[0],parts[1]);
            }
            return new JdbcCypherExecutor(uri);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid Neo4j-ServerURL " + uri);
        }
    }

    public Map findCase(String title) {
        if (title==null) return Collections.emptyMap();
        return IteratorUtil.singleOrNull(cypher.query(
                "MATCH (movie:Movie {title: \""+title+"\"})" +
                 "RETURN movie",
                map("1", title)));
    }
    public boolean addAngles(double[] angles){
       log.info("add angles..");
        if(angles.length!=0){
            int level=0;
            for(int i=0;i<angles.length-1;i++){
                cypher.query("MATCH (n:Intervals{value: "+angles[i]+" }),(m:Intervals{value: "+angles[i+1]+"}) " +
                             "CREATE UNIQUE (n)-[:LEVEL{level: "+level+" }]->(m) return m",map("1",null));
                level++;
            }
          return true;
        }else{
            log.error("nothing to add!");
            return false;
        }
    }
    public Response checkAngles(double[] angles){
        int levelExpected=angles.length-1;
        int levelActual=0;
        log.info("checking angles...");
        TreeSet<String> identityList=new TreeSet<>();
        if(angles.length!=0){
            for(int i=0;i<angles.length-1;i++){
                identityList.add(IteratorUtil.asCollection(cypher.query("START n = node(*)\n" +
                        "MATCH n-[r:LEVEL]->c\n" +
                        "WHERE HAS(n.value) AND HAS(c.value) " +
                        "AND n.value>(" + (angles[i] - CONSTR) + ") AND n.value<(" + (angles[i] + CONSTR) + ")\n" +
                        "AND c.value>(" + (angles[i + 1] - CONSTR) + ") AND c.value<(" + (angles[i + 1] + CONSTR) + ")\n" +
                        "RETURN n, r,c", map("1", i))).toString());
            }
            log.info("map.size =" + identityList.size());
            for(String m:identityList){
                int tmp=ResultPoints.extractMaxLevel(m);
                if(tmp>levelActual) {
                    log.info("founded angles: " + m);
                    levelActual=tmp;
                }
            }
        }else{
            log.error("nothing to search!!");
            return Response.noContent().build();
        }
        log.info("expected level was : "+levelExpected+" ,but actual is : "+levelActual);
        if(Math.abs(levelExpected-levelActual)<=levelActual) {
            log.info("everything is ok!!");
            return Response.ok().build();
        }
        return Response.noContent().build();

    }
    @SuppressWarnings("unchecked")
    public Map<String, Object> graph(int limit) {
        //query
        Iterator<Map<String,Object>> result = cypher.query(
                "MATCH (m:Movie)<-[:ACTED_IN]-(a:Person) " +
                " RETURN m.title as movie, collect(a.name) as cast " +
                " LIMIT {1}", map("1",limit));
        //add nodes
        List nodes = new ArrayList();
        //add rellationships
        List rels= new ArrayList();
        int i=0;
        while (result.hasNext()) {
            Map<String, Object> row = result.next();
            nodes.add(map("title",row.get("movie"),"label","movie"));
            int target=i;
            i++;
            for (Object name : (Collection) row.get("cast")) {
                Map<String, Object> actor = map("title", name,"label","actor");
                int source = nodes.indexOf(actor);
                if (source == -1) {
                    nodes.add(actor);
                    source = i++;
                }
                rels.add(map("source",source,"target",target));
            }
        }
        return map("nodes", nodes, "links", rels);
    }
}
