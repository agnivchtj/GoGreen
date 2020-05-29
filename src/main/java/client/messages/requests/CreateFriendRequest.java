package client.messages.requests;

/**
 * The class that handles sending Create Friend requests.
 */
public class CreateFriendRequest {
    /**
     * @param username the client's username.
     */
    private String username;

    /**
     * @param friend the friend's username.
     */
    private String friend;

    /**
     * Empty constructor.
     */
    public CreateFriendRequest() { }

    /**
     * Constructor for create friend request.
     * @param user the username.
     * @param frnd the friend's username.
     */
    public CreateFriendRequest(final String user, final String frnd) {
        username = user;
        friend = frnd;
    }

    /**
     * Method to get username.
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Method to set the username.
     * @param user the client's username.
     */
    public void setUsername(final String user) {
        username = user;
    }

    /**
     * Method to get friend's username.
     * @return the friend's username.
     */
    public String getFriend() {
        return friend;
    }

    /**
     * Method to set friend's username.
     * @param frnd the friend's username.
     */
    public void setFriend(final String frnd) {
        friend = frnd;
    }
}
