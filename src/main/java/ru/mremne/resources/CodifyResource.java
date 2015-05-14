package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mremne.model.identification.Area;
import ru.mremne.model.identification.Point;
import ru.mremne.model.identification.ResultPoints;
import ru.mremne.service.FidService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
 * date: 06.01.15
 * time: 23:30.
 */
@ManagedBean
@Path("/coder")
@Produces(MediaType.APPLICATION_JSON)
public class CodifyResource {
    private static final Logger LOG =Logger.getLogger(CodifyResource.class);
    @Inject
    private FidService service;
    @POST
    @Path("/codify")
    @Consumes(MediaType.APPLICATION_JSON)
    public void codify(@Suspended final AsyncResponse asyncResponse,final String input) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                LOG.info("new identify");
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode inputJson = mapper.readTree(input);
                    JsonNode pointsJSON = inputJson.path("points");
                    int k = -1;
                    List<Integer> dotsX = new ArrayList<>(), dotsY = new ArrayList<>();
                    ResultPoints resultPoints = new ResultPoints();
                    for (JsonNode point : pointsJSON) {
                        dotsX.add(point.get("x").asInt());
                        dotsY.add(point.get("y").asInt());
                        ++k;
                        resultPoints.putInResultPoints(dotsX.get(k), dotsY.get(k));
                    }
                    List<Point> poin = new ArrayList<>();
                    for (Area a : resultPoints.getPointList()) {
                        System.out.println("Is this right? : " + a.getResultPoint());
                        poin.add(a.getResultPoint());

                    }
                    Double[] angles = getAngleValue(poin);
                    service.addAngles(angles);
                    asyncResponse.resume(ok().build());
                } catch (JsonMappingException e) {
                    LOG.error("json mapping exception");
                    asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                } catch (JsonGenerationException e) {
                    LOG.error("json generation exception");
                    asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                } catch (IOException e) {
                    LOG.error("IO exception");
                    asyncResponse.resume(status(Response.Status.BAD_REQUEST).build());
                }
            }
        }).start();
    }
}
