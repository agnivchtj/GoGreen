package client;

/**
 * This class deals with the request sent by the client
 * during registering.
 */
public class RegistrationRequest {
    /**
     * Initializing the username variable.
     */
    private String username;

    /**
     * Initializing the email variable.
     */
    private String email;

    /**
     * Initializing the password string.
     */
    private String password;

    /**
     * Constructor for registration request.
     * @param user the username submitted by the client.
     * @param emailId the email id filled in.
     * @param pwd the password submitted.
     */
    public RegistrationRequest(
            final String user,
            final String emailId, final String pwd) {
        username = user;
        email = emailId;
        password = pwd;
    }

    /**
     * Empty constructor.
     */
    public RegistrationRequest() { }

    /**
     * Method to get username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to set username.
     * @param user the username.
     */
    public void setUsername(final String user) {
        username = user;
    }

    /**
     * Method to get email.
     * @return the email ID.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set email.
     * @param emailId the email address.
     */
    public void setEmail(final String emailId) {
        email = emailId;
    }

    /**
     * Method to get password.
     * @return the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set password.
     * @param pwd the password.
     */
    public void setPassword(final String pwd) {
        password = pwd;
    }
}
