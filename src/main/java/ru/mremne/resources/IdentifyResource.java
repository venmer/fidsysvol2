package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mremne.model.identification.*;
import ru.mremne.model.mongo.dao.User;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    private static final Logger LOG =Logger.getLogger(IndexResource.class);
    @Inject
    private FidService service;
    @Inject
    private MongoService mongoService;
    @Context
    SecurityContext securityContext;
    @POST
    @Path("/identify")
    @Consumes(MediaType.APPLICATION_JSON)
    public void identify(@Suspended final AsyncResponse asyncResponse,final String input) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Result identiResult=new Result();
                long currentResultTimeStamp=identiResult.getTimestamp();
                LOG.info("time stamp: " + new Date(currentResultTimeStamp).toString());
                identiResult.setStatus(Status.RUNNING);
                identiResult.setIdResult(IdResult.PART);
                LOG.info("new identify");
                ObjectMapper mapper = new ObjectMapper();
                try{
                    JsonNode inputJson=mapper.readTree(input);
                    JsonNode idJson=inputJson.path("id");
                    LOG.info("id: "+idJson.asText());
                    identiResult.setId(idJson.asText());
                    JsonNode pointsJSON=inputJson.path("points");
                    User startUser = mongoService.getUserById(idJson.asText());
                    LOG.info("user: " + startUser);
                    if (startUser == null) {
                        startUser = new User();
                        startUser.setId(idJson.toString());
                   }
                    LOG.info("list: " + startUser.getResults());
                    if (startUser.getResults() == null)
                        startUser.setResults(new ArrayList<Result>());
                    startUser.getResults().add(identiResult);
                    for (Result r : startUser.getResults())
                    LOG.info("result data: " +r.toString());
                    mongoService.saveUser(startUser);
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
                    //too mush points
                    if(resultPoints.getPointList().size()>=9)
                        asyncResponse.resume(Response.status(Response.Status.BAD_REQUEST).build());
                    List<Point> poin=new ArrayList<>();
                    for(Area a:resultPoints.getPointList()){
                        System.out.println("Is this right? : "+a.getResultPoint() );
                        poin.add(a.getResultPoint());
                    }
                    Double[] angles=getAngleValue(poin);

                    identiResult.setId(idJson.toString());
                    identiResult.setStatus(Status.READY);
                    Matching matching = service.checkAngles(angles);
                    LOG.info("match level ex: " + matching.getExpectedLevel());
                    LOG.info("match level act: " + matching.getActualLevel());
                    if (matching.isOrigin()) {
                        LOG.info("is ORIGIN");
                        identiResult.setIdResult(IdResult.ORIGIN);
                    }else{
                        LOG.info("is FAKE");
                        identiResult.setIdResult(IdResult.UNKNOWN);
                    }
                    LOG.info("matching: " + matching.getMatching());
                    identiResult.setMatch(matching.getMatching());
                    LOG.info("identity result : " + identiResult.toString());
                    User tarUser = mongoService.getUserById(startUser.getId());
                    LOG.info("all results... " + tarUser.getResults().size());
                    for (Result r : tarUser.getResults()) {
                        LOG.info("current result: "+r.toString());
                        if (r.getTimestamp() == currentResultTimeStamp && r.getStatus() == Status.RUNNING) {
                            tarUser.getResults().remove(r);
                            tarUser.getResults().add(identiResult);
                        }
                    }
                    mongoService.saveUser(tarUser);
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
       LOG.info("web.page:" + page);
        LOG.info("----------------------------in status-------------------------------");
        LOG.info("status id: "+id);
        LOG.info("valid id: " + ObjectId.isValid(id));
        String output = mongoService.getUserById(id).getResults().toString();
        if(output!=null && (page!= null && page<5)) {
            LOG.info("map output" + output);
            String resultStr = "{\"results\": " + output + "}";
            LOG.info(resultStr);
            return ok(resultStr).build();
        }else{
            return ok().build();
        }
    }

}
