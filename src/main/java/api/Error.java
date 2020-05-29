package api;

/**
This class represents the Error instance of the API.
 */
public class Error extends Response {

    /**
     * Details why the error was generated.
     */
    private String details;

    /**
     * Default constructor with generic error.
     */
    public Error() {
        super("Error");
        this.details = "Incorrect Request";
    }

    /**
     * Custom constructor with custom error.
     * @param custom string containing the error message.
     */
    public Error(final String custom) {
        super("Error");
        this.details = custom;
    }

    /**
     * Getter for the details field.
     * @return a string containing the details of the error.
     */
    public String getDetails() {
        if (this.details != null) {
            return this.details;
        } else {
            return "";
        }
    }

}
