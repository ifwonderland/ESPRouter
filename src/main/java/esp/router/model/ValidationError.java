package esp.router.model;

/**
 * Represents a validation error
 * <p/>
 * Created by Shaochen Huang on 11/26/14.
 */
public class ValidationError {

    public enum ErrorType {
        MissingValue, InvalidEmail
    };

    private ErrorType type;

    private String description;

    public ValidationError(ErrorType type, String description) {
        this.type = type;
        this.description = description;
    }

    public ErrorType getType() {
        return type;
    }

    public void setType(ErrorType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "ValidationError{" +
                "type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
