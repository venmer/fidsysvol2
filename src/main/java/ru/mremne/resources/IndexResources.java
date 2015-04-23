package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.mongo.beans.Product;
import ru.mremne.service.MongoService;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@ManagedBean
@Path("/")
@Consumes(MediaType.TEXT_HTML)
@Produces(MediaType.TEXT_HTML)
@ErrorTemplate(name="/error.ftl")
public class IndexResources {
    @Inject
    private MongoService mongoService;

    @GET
    @Template(name = "/templates/start.ftl")
    public void showMainPage(){
        Product product=new Product();
        product.setType("aspirin");
        product.setDescription("meds");
        mongoService.addProduct(product);

    }
}
