package ru.mremne.resources;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mremne.service.FidService;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * autor:maksim
 * date: 14.01.15
 * time: 23:19.
 */
@RunWith(Parameterized.class)
public class CodifyResourceTest extends JerseyTest {
    private File requestJson;
    private Response expectedResponse;

    public CodifyResourceTest(String jsonPath,Response response){
        this.requestJson=new File(jsonPath);
        this.expectedResponse=response;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {CodifyResourceTest.class.getResource("/coder/test1.json").getPath(),Response.ok().build()},
                {CodifyResourceTest.class.getResource("/coder/test2.json").getPath(),Response.noContent().build()},
                {CodifyResourceTest.class.getResource("/coder/test3.json").getPath(),Response.noContent().build()}
        });
    }
    @Override
    protected Application configure(){
        AbstractBinder binder=new AbstractBinder() {
            @Override
            protected void configure() {
                bind(FidService.class).to(FidService.class);
            }
        };
        ResourceConfig rc=new ResourceConfig(CodifyResource.class);
        rc.register(binder);
        return rc;
    }
    @Test
    public void testCodifyMethod() throws IOException {
        String testJson= Utils.readFile(requestJson);
        testJson=testJson.replace("\\","");
        javax.ws.rs.client.Entity<String> request=
                javax.ws.rs.client.Entity.entity(testJson, MediaType.APPLICATION_JSON);
        final Response actualResponce = target("/coder/codify")
                .request()
                .post(request);

        assertThat(expectedResponse.getStatus(),is(actualResponce.getStatus()));
    }
}
