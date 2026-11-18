package ru.mremne.resources;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private static final Logger LOG =Logger.getLogger(IdentifyResource.class);
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
                    String userId=idJson.asText();
                    LOG.info("id: "+userId);
                    if (userId == null || userId.isEmpty()) {
                        asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                        return;
                    }
                    identiResult.setId(userId);
                    JsonNode pointsJSON=inputJson.path("points");

                    int k = -1;
                    List<Integer> dotsX = new ArrayList<Integer>(), dotsY = new ArrayList<Integer>();
                    ResultPoints resultPoints=new ResultPoints();
                    for (JsonNode point : pointsJSON) {
                        JsonNode x = point.get("x");
                        JsonNode y = point.get("y");
                        if (x == null || y == null) {
                            asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                            return;
                        }
                        dotsX.add(x.asInt());
                        dotsY.add(y.asInt());
                        ++k;
                        resultPoints.putInResultPoints(dotsX.get(k), dotsY.get(k));
                    }
                    //too mush points
                    if(resultPoints.getPointList().size()>=9 || resultPoints.getPointList().isEmpty()) {
                        asyncResponse.resume(Response.status(Response.Status.BAD_REQUEST).build());
                        return;
                    }
                    List<Point> poin=new ArrayList<Point>();
                    for(Area a:resultPoints.getPointList()){
                        LOG.info("Is this right? : "+a.getResultPoint() );
                        poin.add(a.getResultPoint());
                    }
                    Double[] angles=getAngleValue(poin);
                    if (angles.length == 0) {
                        asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                        return;
                    }

                    User startUser = mongoService.getUserById(userId);
                    LOG.info("user: " + startUser);
                    if (startUser == null) {
                        startUser = new User();
                        startUser.setId(userId);
                    }
                    if (startUser.getResults() == null)
                        startUser.setResults(new ArrayList<Result>());
                    startUser.getResults().add(identiResult);
                    mongoService.saveUser(startUser);
                    LOG.info("points: " + pointsJSON.toString());

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
                    if (tarUser == null) {
                        tarUser = startUser;
                    }
                    List<Result> results = tarUser.getResults();
                    if (results == null) {
                        results = new ArrayList<Result>();
                        tarUser.setResults(results);
                    }
                    LOG.info("all results... " + results.size());
                    for (int i = 0; i < results.size(); i++) {
                        Result r = results.get(i);
                        LOG.info("current result: "+r.toString());
                        if (r.getTimestamp() == currentResultTimeStamp && r.getStatus() == Status.RUNNING) {
                            results.set(i, identiResult);
                            break;
                        }
                    }
                    mongoService.saveUser(tarUser);
                    LOG.info("status is save! ");
                    asyncResponse.resume(ok().build());
                } catch (IOException e) {
                    LOG.error("json or IO problem", e);
                    asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                } catch (Exception e) {
                    LOG.error("identify failed", e);
                    asyncResponse.resume(status(Response.Status.INTERNAL_SERVER_ERROR).build());
                }
            }
        }).start();

    }

    @GET
    @Path("/status/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response statusToResponse(@PathParam("id") @NotNull String id,@QueryParam("page") Integer page){
       LOG.info("web.page:" + page);
        LOG.info("----------------------------in status-------------------------------");
        LOG.info("status id: "+id);
        LOG.info("valid id: " + ObjectId.isValid(id));
        User user = mongoService.getUserById(id);
        List<Result> results = user != null ? user.getResults() : null;
        if (results == null) {
            results = new ArrayList<Result>();
        }
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("results", results);
        LOG.info("map output" + body);
        return ok(body).build();
    }

}
