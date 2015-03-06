package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mremne.model.IdResult;
import ru.mremne.model.Result;
import ru.mremne.model.ResultPoints;
import ru.mremne.model.Status;
import ru.mremne.service.FidService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.*;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;

/**
 * autor:maksim
 * date: 14.01.15
 * time: 20:31.
 */
@ManagedBean
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class IdentifyResource {
    @Inject
    private FidService service;
    private static final Logger LOG =Logger.getLogger(IndexResources.class);
    private static Map<String,Result> resultMap=new HashMap<>();
    @POST
    @Path("/identifier/identify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response identify(String input) {
        resultMap.clear();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode inputJson=mapper.readTree(input);
            JsonNode idJson=inputJson.path("id");
            JsonNode pointsJSON=inputJson.path("points");
            LOG.info("points: " + pointsJSON.toString());
            int k = -1;
            List<Integer> dotsX = new ArrayList<>(), dotsY = new ArrayList<>();
            ResultPoints resultPoints=new ResultPoints();
            for (JsonNode point : pointsJSON) {
                dotsX.add(point.get("x").asInt());
                dotsY.add(point.get("y").asInt());
                ++k;
                resultPoints.putInResultPoints(dotsX.get(k), dotsY.get(k));
            }
            SortedSet<Double> angles=ResultPoints.getAngleValue(resultPoints.getPointList());
            double[] ang=new double[angles.size()];
            int i=0;
            for(Double d:angles){
                ang[i]=d;
                i++;
            }
            Result identiResult=new Result();
            identiResult.setId(idJson.toString());
            identiResult.setStatus(Status.READY);
            if(service.checkAngles(ang).getStatus()==Response.ok().build().getStatus()){
                identiResult.setIdResult(IdResult.ORIGIN);
            }else{
                identiResult.setIdResult(IdResult.UNKNOWN);
            }
            resultMap.put(idJson.toString(),identiResult);
            service.saveStatus(identiResult);
            return service.checkAngles(ang);
        } catch (JsonMappingException e) {
            LOG.error("json mapping problem");
            return status(Response.Status.BAD_REQUEST).build();
        } catch (JsonGenerationException e) {
            LOG.error("json generation problem");
            return status(Response.Status.BAD_REQUEST).build();
        } catch (IOException e) {
            LOG.error("IO exception");
        }
        return ok().build();
    }
    @GET
    @Path("/identifier/status/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response statusToResponse(@PathParam("id") String id){
        LOG.info("----------------------------in status-------------------------------");
        ObjectMapper mapper=new ObjectMapper();
        String output = "";
            try {
                output=mapper.writeValueAsString(service.getStatus(id));
            } catch (IOException e) {
                LOG.error("no elements");
            }
            LOG.info("map output"+output);
        return Response.ok("{\"results\": [" + output + "]}").build();
    }
}
