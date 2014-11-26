package esp.router;

import javax.ws.rs.core.Application;

import esp.router.resource.EmailServiceProviderRouter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EmailServiceProviderRouterTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(EmailServiceProviderRouter.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("myresource").request().get(String.class);

        assertEquals("Hello, Heroku!", responseMsg);
    }
}
