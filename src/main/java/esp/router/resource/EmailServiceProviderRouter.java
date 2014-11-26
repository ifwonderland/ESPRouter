package esp.router.resource;

import esp.router.model.EmailDeliveryResult;
import esp.router.model.EmailEnvelope;
import esp.router.model.InvalidEnvelopeException;
import esp.router.model.ValidationError;
import esp.router.util.EmailBodyConverterUtil;
import esp.router.util.ValidationUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("esprouter")
public class EmailServiceProviderRouter {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EmailDeliveryResult send(EmailEnvelope envelope) throws InvalidEnvelopeException {
        if (envelope == null)
            throw new InvalidEnvelopeException("Null envelope passed in. ");

        List<ValidationError> validationErrors = ValidationUtil.validate(envelope);

        if (validationErrors != null && !validationErrors.isEmpty())
            throw new InvalidEnvelopeException(validationErrors);

        String bodyText = EmailBodyConverterUtil.convert(envelope.getBody());


    }


    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Hello, Heroku!";
    }
}
