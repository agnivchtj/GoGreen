package client.messages.responses;

/**
 * The class that deals with how the client handles Create Friend responses.
 */
public class CreateFriendResponse {
    /**
     * Status of the responses: Success/Error.
     */
    private String status;

    /**
     * A class to create activity responses.
     */
    public CreateFriendResponse() { }

    /**
     * Constructor of the class with custom status code.
     * @param customStatus the string containing the status code.
     */
    public CreateFriendResponse(final String customStatus) {
        status = customStatus;
    }

    /**
     * Method to set the status.
     * @param stat the status.
     */
    public void setStatus(final String stat) {
        status = stat;
    }

    /**
     * Getter for the status of the Response.
     * @return a string containing the status code.
     */
    public String getStatus() {
        if (status != null) {
            return status;
        } else {
            return "";
        }
    }
}
