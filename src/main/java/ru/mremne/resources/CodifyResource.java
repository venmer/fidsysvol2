package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mremne.model.identification.ResultPoints;
import ru.mremne.service.FidService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import static javax.ws.rs.core.Response.*;

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
    public Response codify(String input) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode inputJson=mapper.readTree(input);
            JsonNode pointsJSON=inputJson.path("points");
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
             return service.addAngles(ang);

        } catch (JsonMappingException e) {
            LOG.error("json mapping exception");
            return status(Status.BAD_REQUEST).build();
        } catch (JsonGenerationException e) {
            LOG.error("json generation exception");
            return status(Status.BAD_REQUEST).build();
        } catch (IOException e) {
            LOG.error("IO exception");
        }
        return ok().build();
    }
}
