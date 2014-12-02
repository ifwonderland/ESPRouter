package router.service.esp.mandrill;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import router.model.EmailEnvelope;
import router.model.EmailRouteConfig;
import router.model.esp.mandrill.MandrillEmailDeliveryResult;
import router.model.esp.mandrill.MandrillEmailJson;
import router.model.esp.mandrill.MandrillMessageJson;
import router.model.esp.mandrill.SingleEmailDeliveryResult;
import router.service.EmailRouteService;

/**
 * Mandrill service implementation of EmailRouteService
 * Created by shuang on 11/26/2014.
 */
public class MandrillEmailRouteService implements EmailRouteService<MandrillEmailJson, MandrillEmailDeliveryResult> {

    private Logger log = Logger.getLogger(getClass());

    @Override
    public MandrillEmailDeliveryResult send(EmailEnvelope email) throws Exception {
        try {

            MandrillEmailJson emailJson = convert(email);
            String emailJsonString = mapper.writeValueAsString(emailJson);

            WebResource webResource = client
                    .resource("https://mandrillapp.com/api/1.0/messages/send.json");

            ClientResponse response = webResource.accept("application/json")
                    .type("application/json").post(ClientResponse.class, emailJsonString);

            if (response.getStatus() != 200) {
                String errorResponse = response.getEntity(String.class);
                MandrillEmailDeliveryResult result = new MandrillEmailDeliveryResult(getError(errorResponse), null);

                return result;
            }

            String responseString = response.getEntity(String.class);
            SingleEmailDeliveryResult[] singleEmailDeliveryResults = mapper.readValue(responseString, SingleEmailDeliveryResult[].class);


            MandrillEmailDeliveryResult result = new MandrillEmailDeliveryResult(null, singleEmailDeliveryResults);
            return result;


        } catch (Exception e) {
            log.error("Exception happened when trying to send email. ", e);
            throw e;
        }
    }


    /**
     * Parse error response and get its error type
     *
     * @param errorResponse
     * @return
     */
    private MandrillEmailDeliveryResult.Errors getError(String errorResponse) {
        if (StringUtils.isEmpty(errorResponse))
            return MandrillEmailDeliveryResult.Errors.GeneralError;

        if (errorResponse.contains(MandrillEmailDeliveryResult.Errors.Invalid_Key.name()))
            return MandrillEmailDeliveryResult.Errors.Invalid_Key;

        if (errorResponse.contains(MandrillEmailDeliveryResult.Errors.PaymentRequired.name()))
            return MandrillEmailDeliveryResult.Errors.PaymentRequired;
        if (errorResponse.contains(MandrillEmailDeliveryResult.Errors.Unknown_Subaccount.name()))
            return MandrillEmailDeliveryResult.Errors.Unknown_Subaccount;
        if (errorResponse.contains(MandrillEmailDeliveryResult.Errors.ValidationError.name()))
            return MandrillEmailDeliveryResult.Errors.ValidationError;
        if (errorResponse.contains(MandrillEmailDeliveryResult.Errors.GeneralError.name()))
            return MandrillEmailDeliveryResult.Errors.GeneralError;

        return MandrillEmailDeliveryResult.Errors.UnrecoverableError;
    }


    @Override
    public MandrillEmailJson convert(EmailEnvelope emailEnvelope) throws Exception {
        if (emailEnvelope == null) {
            log.warn("Email envelope passed in is null. ");
            return null;
        }

        MandrillMessageJson.ToJson[] toJsons = new MandrillMessageJson.ToJson[1];
        MandrillMessageJson.ToJson toJson = new MandrillMessageJson.ToJson(emailEnvelope.getTo(), emailEnvelope.getTo_name(), "to");
        toJsons[0] = toJson;

        MandrillMessageJson.HeadersJson headersJson = new MandrillMessageJson.HeadersJson(emailEnvelope.getFrom());

        MandrillMessageJson mesageJson = new MandrillMessageJson("", emailEnvelope.getBody(), emailEnvelope.getSubject(), emailEnvelope.getFrom_name(), emailEnvelope.getFrom(), toJsons, headersJson);

        MandrillEmailJson emailJson = new MandrillEmailJson(routeConfig.getApiConfig().getApiKey(), mesageJson);

        return emailJson;
    }


    private EmailRouteConfig routeConfig;
    private Client client;
    private ObjectMapper mapper;

    public MandrillEmailRouteService(EmailRouteConfig routeConfig) {
        this.routeConfig = routeConfig;

        ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(clientConfig);

        mapper = new ObjectMapper();
    }
}
