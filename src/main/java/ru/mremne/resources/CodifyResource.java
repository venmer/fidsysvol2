package ru.mremne.resources;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import ru.mremne.model.ResultPoints;
import ru.mremne.service.FidService;
import ru.mremne.util.Util;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;

import static javax.ws.rs.core.Response.*;

/**
 * autor:maksim
 * date: 06.01.15
 * time: 23:30.
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CodifyResource {
    private static final Logger log=Logger.getLogger(IndexResources.class);
    final FidService service = new FidService(Util.getNeo4jUrl());
    @POST
    @Path("/coder/codify")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response codify(String input) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode inputJson=mapper.readTree(input);
            JsonNode pointsJSON=inputJson.path("points");
            mapper.writeValue(new File("/home/maksim/test1.json"),input);
            System.out.println("points: " + pointsJSON.toString());
            int k = -1;
            ArrayList<Integer> dotsX = new ArrayList<>(), dotsY = new ArrayList<>();
            ResultPoints resultPoints=new ResultPoints();
            for (JsonNode point : pointsJSON) {
                dotsX.add(point.get("x").asInt());
                dotsY.add(point.get("y").asInt());
                ++k;
                resultPoints.putInResultPoints(dotsX.get(k), dotsY.get(k));

            }
            TreeSet<Double> angles=ResultPoints.getAngleValue(resultPoints.getPointList());
            double[] ang=new double[angles.size()];
            int i=0;
            for(Double d:angles){
                ang[i]=d;
                i++;
            }
             return service.addAngles(ang) ? ok().build() : noContent().build();

        } catch (JsonMappingException e) {
            e.printStackTrace();
            return status(Status.BAD_REQUEST).build();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
            return status(Status.BAD_REQUEST).build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ok().build();
    }
}
