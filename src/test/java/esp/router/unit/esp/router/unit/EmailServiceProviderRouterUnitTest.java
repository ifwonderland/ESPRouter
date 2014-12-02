package esp.router.unit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import router.model.AbstractEmailDeliveryResult;
import router.model.EmailEnvelope;
import router.resource.EmailServiceProviderRouter;

/**
 * Simple unit test caes
 * <p/>
 * Created by shuang on 12/1/2014.
 */
public class EmailServiceProviderRouterUnitTest {
    private EmailServiceProviderRouter router = null;

    @Before
    public void setUp() throws Exception {
        router = new EmailServiceProviderRouter();
    }

    @Test
    public void testSend() throws Exception {
        EmailEnvelope testEnvelope = new EmailEnvelope("ifwonderland@gmail.com", "ifwonderland", "ifwonderland@gmail.com", "ifwonderland", "test subject", "<html> test sent email this is email body. </html>");

        AbstractEmailDeliveryResult result = router.send(testEnvelope);

        Assert.assertNotNull(result);
    }


}
