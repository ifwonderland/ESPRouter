package router.model.esp.mandrill;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Created by shuang on 12/1/2014.
 */
public class SingleEmailDeliveryResult {
    private String email;
    private DeliveryStatus status;
    private String id;
    private RejectionReason rejectionReason;

    @JsonCreator
    public SingleEmailDeliveryResult(@JsonProperty("email") String email, @JsonProperty("status") DeliveryStatus status, @JsonProperty("_id") String id, @JsonProperty("reject_reason") RejectionReason rejectionReason) {
        this.email = email;
        this.status = status;
        this.rejectionReason = rejectionReason;
        this.id = id;
    }

    public enum DeliveryStatus {
        sent, queued, scheduled, rejected, invalid
    }

    public enum RejectionReason {
        hard_bounce, soft_bounce, spam, unsub, custom, invalid_sender, invalid, test_mode_limit, rule
    }
}
