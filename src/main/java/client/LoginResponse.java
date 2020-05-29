package client;

/**
 * This class deals with how the client handles the responses from the server.
 */
public class LoginResponse {
    /**
     * Initializing the status string.
     */
    private String status;

    /**
     * Initializing the details string.
     */
    private String details;

    /**
     * Empty constructor.
     */
    public LoginResponse() { }

    /**
     * Constructor for login responses class.
     * @param stat the status of the request.
     * @param dtls the details.
     */
    public LoginResponse(final String stat, final String dtls) {
        status = stat;
        details = dtls;
    }

    /**
     * Method to get the status of the responses.
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method to set a new status for the responses.
     * @param stat the status.
     */
    public void setStatus(final String stat) {
        status = stat;
    }

    /**
     * Method to get the details of a responses.
     * @return the details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Method to set new details of a responses.
     * @param dtls the details.
     */
    public void setDetails(final String dtls) {
        details = dtls;
    }
}
