package router.model;

import java.util.List;

/**
 * Recoverable checked exception representing an invalid envelope error.
 *
 * Created by Shaochen Huang on 11/26/14.
 */
public class InvalidEnvelopeException extends Exception{

    private List<ValidationError> errors;


    public InvalidEnvelopeException(String message) {
        super(message);
    }

    public InvalidEnvelopeException(List<ValidationError> errors) {
        super("Invalid envelope, "+errors.toString());
        this.errors = errors;
    }
}
