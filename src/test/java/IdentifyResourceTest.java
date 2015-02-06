import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.mremne.resources.IdentifyResource;

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
 * date: 16.01.15
 * time: 2:26.
 */
@RunWith(Parameterized.class)
public class IdentifyResourceTest extends JerseyTest {
    private File requestJson;
    private Response expectedResponse;
    public IdentifyResourceTest(String jsonPath,Response response){
        this.requestJson=new File(jsonPath);
        this.expectedResponse=response;
    }
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {CodifyResourceTest.class.getResource("decoder/test1.json").getPath(), Response.ok().build()},
                {CodifyResourceTest.class.getResource("decoder/test2.json").getPath(), Response.noContent().build()},
                {CodifyResourceTest.class.getResource("decoder/test3.json").getPath(), Response.noContent().build()}
        });
    }
    @Override
    protected Application configure(){
        return new ResourceConfig(IdentifyResource.class);
    }
    @Test
    public void testIdentifyMethod() throws IOException {
        String testJson=Utils.readFile(requestJson);
        testJson=testJson.replace("\\","");
        javax.ws.rs.client.Entity<String> request=
                javax.ws.rs.client.Entity.entity(testJson, MediaType.APPLICATION_JSON);
        final Response actualResponce = target("/identifier/identify")
                .request()
                .post(request);
        System.out.println(actualResponce.getStatusInfo());
        assertThat(expectedResponse.getStatus(), is(actualResponce.getStatus()));

    }
}
