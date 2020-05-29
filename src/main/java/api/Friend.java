package api;

/**
 * Class represent a friend of a user in the database.
 */
public class Friend {

    /**
     * Username of the friend.
     */
    private String username;

    /**
     * Constructor that set the username of the friend.
     * @param name is the username of the friend.
     */
    public Friend(final String name) {
        this.username = name;
    }

    /**
     * Get the username of the friend.
     * @return a string containing the username of the friend.
     */
    public String getUsername() {
        if (this.username != null) {
            return this.username;
        } else {
            return "";
        }
    }
}
