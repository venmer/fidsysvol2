package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mremne.model.identification.*;
import ru.mremne.model.identification.Status;
import ru.mremne.model.mongo.dao.Result;
import ru.mremne.service.FidService;
import ru.mremne.service.MongoService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import static javax.ws.rs.core.Response.*;

/**
 * autor:maksim
 * date: 14.01.15
 * time: 20:31.
 */
@ManagedBean
@Path("/identifier")
@Produces(MediaType.APPLICATION_JSON)
public class IdentifyResource {
    @Inject
    private FidService service;
    @Inject
    private MongoService mongoService;
    private static final Logger LOG =Logger.getLogger(IndexResources.class);
    @POST
    @Path("/identify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response identify(String input) {
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
            List<Point> poin=new ArrayList<>();
            for(Area a:resultPoints.getPointList()){
                System.out.println("Is this right? : "+a.getResultPoint() );
                poin.add(a.getResultPoint());

            }
            SortedSet<Double> angles=ResultPoints.getAngleValue(poin);
            double[] ang=new double[angles.size()];
            int i=0;
            for(Double d:angles){
                System.out.println("angle to identify: "+d);
                ang[i]=d;
                i++;
            }
            Result identiResult=new Result();
            identiResult.setFakeId(idJson.toString());
            identiResult.setStatus(Status.READY);
            boolean check=service.checkAngles(ang);
            if(check){
                identiResult.setIdResult(IdResult.ORIGIN);
            }else{
                identiResult.setIdResult(IdResult.UNKNOWN);
            }
            LOG.info("saving status... ");
            mongoService.saveResult(identiResult);
            LOG.info("status is save! ");
            return (check? ok().build(): noContent().build());
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
    @Path("/status/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response statusToResponse(@PathParam("id") @NotNull String id){
        LOG.info("----------------------------in status-------------------------------");
        LOG.info("status id: "+id);
        LOG.info("valid id: " + ObjectId.isValid(id));
        ObjectMapper mapper=new ObjectMapper();
        String output = "";
            try {
                output = mapper.writeValueAsString(mongoService.getResult(id));
            } catch (IOException e) {
                LOG.error("no elements");
            }
            LOG.info("map output" + output);
        return ok("{\"results\": [" + output + "]}").build();
    }
}
