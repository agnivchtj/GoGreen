package api;

import java.util.List;

/**
 * Class containing a list of friend objects.
 */
public class Friends extends Response {

    /**
     * List of object of type friend.
     */
    private List<Friend> friends;

    /**
     * Constructor get a list containing all the friends.
     * @param friend is the list of friends.
     */
    public Friends(final List<Friend> friend) {
        super("Success");
        this.friends = friend;
    }

    /**
     * Getter for the friends list.
     * @return a List containing Friend objects.
     */
    public List<Friend> getFriends() {
        if (this.friends != null) {
            return this.friends;
        } else {
            return null;
        }
    }
}
