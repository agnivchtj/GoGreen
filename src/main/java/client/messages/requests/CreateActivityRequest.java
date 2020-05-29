package client.messages.requests;

/**
 * Created the class which enables the client to create an activity request.
 */
public class CreateActivityRequest {
    /**
     * @param username the username.
     */
    private String username;

    /**
     * @param type the type of activity.
     */
    private String type;

    /**
     * @param comment any extra comment.
     */
    private String comment;


    /**
     * A class to create an activity request.
     */
    public CreateActivityRequest() { }

    /**
     * Constructor for create activity request.
     * @param user takes the username as a parameter.
     * @param typ  takes the type as a parameter.
     * @param comments takes the comment as a parameter.
     */
    public CreateActivityRequest(final String user,
                                 final String typ, final String comments) {
        username = user;
        type = typ;
        comment = comments;
    }

    /**
     * Method to get username.
     * @return - returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to set username.
     * @param user - set the username.
     */
    public void setUsername(final String user) {
        username = user;
    }

    /**
     * Method to get type.
     * @return - the type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method to set the type.
     * @param typ - set the type.
     */
    public void setType(final String typ) {
        type = typ;
    }

    /**
     * Method to get the comment.
     * @return comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Method to set the comment.
     * @param comments - set the comment.
     */
    public void setComment(final String comments) {
        comment = comments;
    }
}
