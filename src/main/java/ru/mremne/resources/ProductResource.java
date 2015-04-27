package ru.mremne.resources;

import org.bson.types.ObjectId;
import org.glassfish.jersey.server.mvc.Template;
import ru.mremne.model.mongo.dao.Product;
import ru.mremne.view.ViewData;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * autor:maksim
 * date: 27.04.15
 * time: 17:27.
 */
@Path("/product")
public class ProductResource {

    @GET
    @Path("/all")
    @Template(name="/templates/mainpage.ftl")
    public ViewData test(){
        ViewData viewData=new ViewData();
        Product product=new Product();
        product.setId(new ObjectId());
        product.setType("test");
        product.setDescription("test");
        viewData.setSingleProduct(product);
            return viewData;
            }

}
