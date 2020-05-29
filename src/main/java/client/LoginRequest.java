package client;

/**
 * This class deals with the request sent by the client during login.
 */
public class LoginRequest {
    /**
     * Initializing the method variable.
     */
    private String method;

    /**
     * Initializing the identity variable.
     */
    private String identity;

    /**
     * Initializing the password variable.
     */
    private String password;

    /**
     * Constructor for the login request.
     * @param meth the method, or the username.
     * @param id this is the id of the user.
     * @param pwd the password created/ matched.
     */
    public LoginRequest(final String meth, final String id, final String pwd) {
        method = meth;
        identity = id;
        password = pwd;
    }

    /**
     * Method to get the identity.
     * @return the identity.
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * Method to set a new identity.
     * @param id the identity.
     */
    public void setIdentity(final String id) {
        identity = id;
    }

    /**
     * Method to get the method of the login request.
     * @return the method.
     */
    public String getMethod() {
        return method;
    }

    /**
     * Method to set a new login method.
     * @param meth the method.
     */
    public void setMethod(final String meth) {
        method = meth;
    }

    /**
     * Method to get the password of the user.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set a new password of the user.
     * @param pwd the password.
     */
    public void setPassword(final String pwd) {
        password = pwd;
    }
}
