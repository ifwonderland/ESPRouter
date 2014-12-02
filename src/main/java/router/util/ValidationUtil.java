package router.util;

import router.model.EmailEnvelope;
import router.model.ValidationError;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Util for validations.
 * <p/>
 * Created by Shaochen Huang on 11/26/14.
 */
public class ValidationUtil {

    private final static Logger log = Logger.getLogger(ValidationUtil.class);

    private final static EmailValidator emailValidator = EmailValidator.getInstance();
    /**
     * Validate EmailEnvelope.
     *
     * @param envelope
     * @return list of ValidationError
     */
    public static List<ValidationError> validate(EmailEnvelope envelope) {
        if (envelope == null) {
            log.warn("Null envelope passed in, nothing to validate, potential for NPE. ");
            return null;
        }
        List<ValidationError> errors = new ArrayList<>();
        if(StringUtils.isEmpty(envelope.getTo()))
            errors.add(new ValidationError(ValidationError.ErrorType.MissingValue, "To field is missing. "));

        if(StringUtils.isEmpty(envelope.getTo_name()))
            errors.add(new ValidationError(ValidationError.ErrorType.MissingValue, "To name field is missing. "));

        if(StringUtils.isEmpty(envelope.getFrom()))
            errors.add(new ValidationError(ValidationError.ErrorType.MissingValue, "From field is missing. "));

        if(StringUtils.isEmpty(envelope.getFrom_name()))
            errors.add(new ValidationError(ValidationError.ErrorType.MissingValue, "From name field is missing. "));

        if(StringUtils.isEmpty(envelope.getSubject()))
            errors.add(new ValidationError(ValidationError.ErrorType.MissingValue, "Email subject is missing. "));

        if(StringUtils.isEmpty(envelope.getBody()))
            errors.add(new ValidationError(ValidationError.ErrorType.MissingValue, "Email body is missing. "));


        if(!emailValidator.isValid(envelope.getTo()))
            errors.add(new ValidationError(ValidationError.ErrorType.InvalidEmail, "To email address is invalid, this could be either format, or domain, or the user is invalid. "));

        if(!emailValidator.isValid(envelope.getFrom()))
            errors.add(new ValidationError(ValidationError.ErrorType.InvalidEmail, "To email address is invalid, this could be either format, or domain, or the user is invalid. "));

        return errors;
    }


}
