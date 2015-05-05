package ru.mremne.resources;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.identification.ResultPoints;
import ru.mremne.service.FidService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;

import static javax.ws.rs.core.Response.noContent;
import static javax.ws.rs.core.Response.ok;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@Path("/")
@Produces("text/html")
public class IndexResources {
    @Inject
    private FidService service;

    @GET
    @Template(name = "/templates/welcome.ftl")
    public String welcomePage() {
        return "welcome";
    }
    @GET
    @Path("/try")
    @Template(name="/templates/fidsysdemo/step_one.ftl")
    public String tryIt(){
        return "try it";
    }
    @GET
    @Path("/try/desc")
    @Template(name="/templates/fidsysdemo/step_two.ftl")
    public String stepTwoPage(){
        System.out.print("int try two page");
        return "ok";
    }
    @POST
    @Path("/try/codify")
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
        SortedSet<Double> angles=ResultPoints.getAngleValue(resultPoints.getPointList());
        double[] ang=new double[angles.size()];
        int i=0;
        String angleSet="";
        for(Double d:angles){
            ang[i]=d;
            System.out.println("angle: "+d);
            angleSet+=d+"|";
            i++;
        }
        if(service.addAngles(ang)) {
            return Response.ok().header("header", true).
                    header("angle_set", angleSet).build();
        }else{
            return  Response.ok().header("header",true).build();
        }
    }
    @POST
    @Path("/try/identify")
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
            SortedSet<Double> angles=ResultPoints.getAngleValue(resultPoints.getPointList());
            double[] ang=new double[angles.size()];
            int i=0;
            for(Double d:angles){
                System.out.println("angle: "+d);
                ang[i]=d;
                i++;
            }
            return (service.checkAngles(ang)? ok().header("header",true).build(): noContent().build());
        } catch (JsonMappingException e) {
            return ok().header("header",false).build();
        } catch (JsonGenerationException e) {
            return ok().header("header",false).build();
        } catch (IOException e) {
        }
        return ok().header("header",true).build();
    }
}
