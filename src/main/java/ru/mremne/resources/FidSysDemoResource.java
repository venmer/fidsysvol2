package ru.mremne.resources;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.identification.Area;
import ru.mremne.model.identification.Point;
import ru.mremne.model.identification.ResultPoints;
import ru.mremne.service.FidService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;
import static javax.ws.rs.core.Response.status;
import static ru.mremne.model.identification.FidUtils.getAngleValue;

/**
 * autor:maksim
 * date: 11.05.15
 * time: 1:03.
 */
@Path("/try")
@Produces("text/html")
public class FidSysDemoResource {
    @Inject
    private FidService service;
    @GET
    @Path("/")
    @Template(name="/templates/fidsysdemo/step_one.ftl")
    public String tryIt(){
        return "try it";
    }
    @GET
    @Path("/desc")
    @Template(name="/templates/fidsysdemo/step_two.ftl")
    public String stepTwoPage(){
        System.out.print("int try two page");
        return "ok";
    }
    @POST
    @Path("/codify")
    @Template(name="/templates/fidsysdemo/step_two.ftl")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tryCodify(String o){
        ObjectMapper mapper = new ObjectMapper();
        System.out.println("points: "+o.toString());
        JsonNode inputJson= null;
        try {
            inputJson = mapper.readTree(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int k = -1;
        List<Integer> dotsX = new ArrayList<>(), dotsY = new ArrayList<>();
        ResultPoints resultPoints=new ResultPoints();
        for (JsonNode point : inputJson) {
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
        if(service.addAngles(angles)) {
            return Response.ok().header("header", true).
                    header("angle_set", Arrays.asList(angles)).build();
        }else{
            return  Response.status(503).build();
        }
    }
    @POST
    @Path("/identify")
    @Template(name="/templates/fidsysdemo/step_two.ftl")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response tryIdentify(String o) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode inputJson=mapper.readTree(o);
            int k = -1;
            List<Integer> dotsX = new ArrayList<>(), dotsY = new ArrayList<>();
            ResultPoints resultPoints=new ResultPoints();
            for (JsonNode point : inputJson) {
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
            return (service.checkAngles(angles)? ok().header("header",true).build(): noContent().build());
        } catch (JsonMappingException e) {
            return status(503).build();
        } catch (JsonGenerationException e) {
            return status(503).build();
        } catch (IOException e) {
        }
        return status(503).build();
    }
}
