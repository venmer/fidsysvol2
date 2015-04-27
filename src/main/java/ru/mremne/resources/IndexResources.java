package ru.mremne.resources;

import org.glassfish.jersey.server.mvc.ErrorTemplate;
import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.service.MongoService;
import ru.mremne.view.ViewData;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * autor:maksim
 * date: 28.12.14
 * time: 23:16.
 */
@ManagedBean
@Path("/")
@ErrorTemplate(name="/error.ftl")
public class IndexResources {
    @Inject
    private MongoService mongoService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Template(name="/templates/mainlayout.ftl")
    public ViewData showMainPage(){
      /*  Product product=new Product();
        product.setType("aspirin");
        product.setDescription("meds");
        mongoService.addProduct(product);*/
        ViewData viewData=new ViewData();
        viewData.setAllProducts( mongoService.getAllProducts().asList());
        System.out.println("size of view data: "+viewData.getAllProducts().size());
        return viewData;

    }
    @GET
    @Path("/products")
    @Produces(MediaType.TEXT_HTML)
    @Template(name = "/templates/mainpage.ftl")
    public ViewData showProducts() {
        ViewData viewData=new ViewData();
        viewData.setAllProducts( mongoService.getAllProducts().asList());
        System.out.println("size of view data: "+viewData.getAllProducts().size());
        return viewData;
    }
    @GET
    @Path("/test")
    @Template(name = "/templates/mainpage.ftl")
    public List test(){
        List<String> keywords=new ArrayList<String>();
        keywords.add("test");
        keywords.add("another");
        return keywords;
    }
}
