package router.service.esp.mailgun;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import router.model.EmailEnvelope;
import router.model.EmailRouteConfig;
import router.model.esp.mailgun.MailgunEmailDeliveryResult;
import router.model.esp.mailgun.MailgunEmailJson;
import router.service.EmailRouteService;
import router.util.EmailBodyConverterUtil;

import javax.ws.rs.core.MediaType;

/**
 * Created by shuang on 12/1/2014.
 */
public class MailgunEmailRouteService implements EmailRouteService<MailgunEmailJson, MailgunEmailDeliveryResult> {
    @Override
    public MailgunEmailDeliveryResult send(EmailEnvelope envelope) throws Exception {
        MailgunEmailJson emailJson = convert(envelope);

        WebResource webResource =
                client.resource(routeConfig.getApiConfig().getServer());
        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", emailJson.getFrom());
        formData.add("to", emailJson.getTo());
        formData.add("subject", emailJson.getSubject());
        formData.add("text", emailJson.getText());

        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                post(ClientResponse.class, formData);

        int status = response.getStatus();

        String responseString = response.getEntity(String.class);

        if(status!=200){
            MailgunEmailDeliveryResult result = new MailgunEmailDeliveryResult("", "");
            result.setError(MailgunEmailDeliveryResult.Error.Failed);
            result.setErrorString(responseString);
            return result;
        }

        return mapper.readValue(responseString, MailgunEmailDeliveryResult.class);
    }

    @Override
    public MailgunEmailJson convert(EmailEnvelope emailEnvelope) throws Exception {
        if (emailEnvelope == null) {
            log.warn("Email envelope passed in is null. ");
            return null;
        }

        String from = emailEnvelope.getFrom_name() + " <" + emailEnvelope.getFrom() + ">";
        String to = emailEnvelope.getTo();
        String subject = emailEnvelope.getSubject();
        String body = EmailBodyConverterUtil.convert(emailEnvelope.getBody());

        MailgunEmailJson emailJson = new MailgunEmailJson(from, to, subject, body);
        return emailJson;
    }

    private EmailRouteConfig routeConfig;
    private Client client;
    private ObjectMapper mapper;

    private Logger log = Logger.getLogger(getClass());

    public MailgunEmailRouteService(EmailRouteConfig routeConfig) {
        this.routeConfig = routeConfig;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(clientConfig);

        this.client.addFilter(new HTTPBasicAuthFilter("api", routeConfig.getApiConfig().getApiKey()));

        mapper = new ObjectMapper();
    }

}
