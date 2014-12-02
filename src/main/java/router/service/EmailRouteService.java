package router.service;

import router.model.AbstractEmailJson;
import router.model.AbstractEmailDeliveryResult;
import router.model.EmailEnvelope;

/**
 * Created by shuang on 11/26/2014.
 */
public interface EmailRouteService<T extends AbstractEmailJson, P extends AbstractEmailDeliveryResult> {

    /**
     * Send email to configured/dynamically found ESP
     *
     * @param envelope
     * @return
     */
    P send(EmailEnvelope envelope) throws Exception;


    /**
     * Convert email envelope metadata to ESP specific JSON
     *
     * @param emailEnvelope
     * @return
     */
    T convert(EmailEnvelope emailEnvelope) throws Exception;


}
