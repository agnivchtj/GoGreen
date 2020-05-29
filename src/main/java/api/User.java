package api;

/**
This class represent the user of the application.
 */
public class User extends Response {

    /**
     * Id of the respective user in the database.
     */
    private int id;

    /**
     * Default constructor for the User class.
     * @param status if request was successful.
     * @param idUser of the user respectively found in the database.
     */
    public User(final String status, final int idUser) {
        super("Success");
        this.id = idUser;
    }

    /**
     * Getter for the user id.
     * @return an integer with the id of the user.
     */
    public int getId() {
        return this.id;
    }
}
