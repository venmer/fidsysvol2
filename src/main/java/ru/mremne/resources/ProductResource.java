package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.service.MongoService;
import ru.mremne.view.ViewData;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 17:27.
 */
@Path("/products")
public class ProductResource {
    @Inject
    private MongoService mongoService;

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/templates/products.ftl")
    public ViewData showProducts() {
        ViewData viewData=new ViewData();
        viewData.setAllProducts( mongoService.getAllProducts().asList());
        System.out.println("size of view data: "+viewData.getAllProducts().size());
        return viewData;
    }



}
