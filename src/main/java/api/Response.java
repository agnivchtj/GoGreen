package api;

/**
 * Main class for the responses of the api.
 */
public class Response {

    /**
     * Status of the responses: Success/Error.
     */
    private String status;

    /**
     * Constructor of the class with custom status code.
     * @param customStatus string containing the status code.
     */
    public Response(final String customStatus) {
        this.status  = customStatus;
    }

    /**
     * Getter for the status of the Response.
     * @return a string containing the status code.
     */
    public String getStatus() {
        if (this.status != null) {
            return this.status;
        } else {
            return "";
        }
    }
}
