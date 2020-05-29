package client;

/**
 * This class deals with how the client handles the responses from server.
 */
public class RegistrationResponse {
    /**
     * Initializing the status variable.
     */
    private String status;

    /**
     * Initializing the identity variable.
     */
    private int id;

    /**
     * Initializing the details.
     */
    private String details;

    /**
     * Empty constructor.
     */
    public RegistrationResponse() { }

    /**
     * Constructor for registration responses.
     * @param stat the status.
     * @param identity the id.
     */
    public RegistrationResponse(final String stat, final int identity) {
        status = stat;
        id = identity;
    }

    /**
     * Method to get details.
     * @return the details.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Method to set details.
     * @param dtls the details.
     */
    public void setDetails(final String dtls) {
        details = dtls;
    }

    /**
     * Method to get status.
     * @return the status.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Method to set status.
     * @param stat the status.
     */
    public void setStatus(final String stat) {
        status = stat;
    }

    /**
     * Method to get id.
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Method to set id.
     * @param identity the identity.
     */
    public void setId(final int identity) {
        id = identity;
    }
}
