package router.resource;

import org.apache.log4j.Logger;
import router.model.*;
import router.service.EmailRouteService;
import router.service.esp.mailgun.MailgunEmailRouteService;
import router.service.esp.mandrill.MandrillEmailRouteService;
import router.util.ConfigurationUtil;
import router.util.EmailBodyConverterUtil;
import router.util.ValidationUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("esprouter")
public class EmailServiceProviderRouter {

    private Logger log = Logger.getLogger(getClass());

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AbstractEmailDeliveryResult send(EmailEnvelope envelope) throws Exception {
        if (envelope == null)
            throw new InvalidEnvelopeException("Null envelope passed in. ");

        List<ValidationError> validationErrors = ValidationUtil.validate(envelope);

        if (validationErrors != null && !validationErrors.isEmpty())
            throw new InvalidEnvelopeException(validationErrors);

        String bodyText = EmailBodyConverterUtil.convert(envelope.getBody());

        envelope.setBody(bodyText);

        return routeService.send(envelope);
    }


    private EmailRouteService routeService;


    public EmailServiceProviderRouter() throws Exception {
        EmailRouteConfig config = ConfigurationUtil.loadEmailRouteConfig();
        switch (config.getEmailServiceProvider()) {
            case Mandrill:
                this.routeService = new MandrillEmailRouteService(config);
                break;
            case Mailgun:
                this.routeService = new MailgunEmailRouteService(config);
                break;
        }

    }
}
