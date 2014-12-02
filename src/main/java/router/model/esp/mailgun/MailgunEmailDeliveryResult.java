package router.model.esp.mailgun;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import router.model.AbstractEmailDeliveryResult;

/**
 * Delivery result for Mailgun
 * Created by shuang on 12/1/2014.
 */
public class MailgunEmailDeliveryResult extends AbstractEmailDeliveryResult {


    public enum Error {
        Failed
    }

    @JsonIgnore
    private Error error;



    @JsonIgnore
    private String errorString;


    private String message;

    private String id;

    @JsonCreator
    public MailgunEmailDeliveryResult(@JsonProperty("message") String message, @JsonProperty("id") String id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public String getId() {
        return id;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getErrorString() {
        return errorString;
    }

    public void setErrorString(String errorString) {
        this.errorString = errorString;
    }
}
