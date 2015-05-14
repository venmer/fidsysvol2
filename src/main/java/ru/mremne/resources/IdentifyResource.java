package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mremne.model.identification.Area;
import ru.mremne.model.identification.IdResult;
import ru.mremne.model.identification.Point;
import ru.mremne.model.identification.ResultPoints;
import ru.mremne.model.mongo.dao.identification.Result;
import ru.mremne.model.mongo.dao.identification.Status;
import ru.mremne.service.FidService;
import ru.mremne.service.MongoService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;
import static ru.mremne.model.identification.FidUtils.getAngleValue;

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
    public void identify(@Suspended final AsyncResponse asyncResponse,final String input) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LOG.info("new identify");
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
                    Double[] angles=getAngleValue(poin);
                    Result identiResult=new Result();
                    identiResult.setId(idJson.toString());
                    identiResult.setStatus(Status.READY);
                    boolean check=service.checkAngles(angles);
                    if(check){
                        identiResult.setIdResult(IdResult.ORIGIN);
                    }else{
                        identiResult.setIdResult(IdResult.UNKNOWN);
                    }
                    LOG.info("saving status... ");
                    mongoService.saveResult(identiResult);
                    LOG.info("status is save! ");
                  asyncResponse.resume(ok().build());
                } catch (JsonMappingException e) {
                    LOG.error("json mapping problem");
                    asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                } catch (JsonGenerationException e) {
                    LOG.error("json generation problem");
                    asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                } catch (IOException e) {
                    LOG.error("IO exception");
                    asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                }
            }
        }).start();

    }

    @GET
    @Path("/status/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response statusToResponse(@PathParam("id") @NotNull String id,@QueryParam("page") Integer page){
       LOG.info("page:" + page);
        LOG.info("----------------------------in status-------------------------------");
        LOG.info("status id: "+id);
        LOG.info("valid id: " + ObjectId.isValid(id));
        String output = mongoService.getResult(id).toString();
        if(output!=null && page<5) {
            LOG.info("map output" + output);
            String resultStr = "{\"results\": [" + output + "]}";
            LOG.info(resultStr);
            return ok(resultStr).build();
        }else{
            return ok().build();
        }
    }

}
