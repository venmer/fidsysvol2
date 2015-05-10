package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.service.MongoService;
import ru.mremne.view.ViewResultData;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@Path("/")
@Produces("text/html")
public class IndexResources {
    @Inject
    private MongoService mongoService;

    @GET
    @Template(name = "/templates/welcome.ftl")
    public String welcome(){
        return "welcome!";
    }
    @GET
    @Path("/results")
    @Template(name = "/templates/results.ftl")
    public ViewResultData showProducts() {
        ViewResultData viewData=new ViewResultData();
        viewData.setResults(mongoService.getAllResults().asList());
        System.out.println("size of resultsList: "+viewData.getResults().size());
        return viewData;
    }

}
