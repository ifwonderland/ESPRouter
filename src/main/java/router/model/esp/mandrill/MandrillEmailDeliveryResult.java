package router.model.esp.mandrill;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import router.model.AbstractEmailDeliveryResult;

/**
 * Created by shuang on 11/30/2014.
 */
public class MandrillEmailDeliveryResult extends AbstractEmailDeliveryResult {
    private Errors error;
    private SingleEmailDeliveryResult[] deliveryResults;

    public MandrillEmailDeliveryResult(Errors error, SingleEmailDeliveryResult[] deliveryResults) {
        this.error = error;
        this.deliveryResults = deliveryResults;
    }


    public enum Errors {
        Invalid_Key, PaymentRequired, Unknown_Subaccount, ValidationError, GeneralError, UnrecoverableError
    }

}
