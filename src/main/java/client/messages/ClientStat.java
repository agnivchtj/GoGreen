package client.messages;

/**
 * The class that allows the user to get the statistics of their friends.
 */
public class ClientStat {
    /**
     * @param username the username of a user.
     */
    private String username;

    /**
     * @param points how many points they have.
     */
    private int points;

    /**
     * @param saved how much co2 they have saved.
     */
    private double saved;

    /**
     * Constructor for client stat.
     * @param user the username.
     * @param poi the points.
     * @param save co2 saved.
     */
    public ClientStat(final String user, final int poi, final double save) {
        this.username = user;
        this.points = poi;
        this.saved = save;
    }

    /**
     * Method to get username.
     * @return the username of the user.
     */
    public String getUsername() {
        if (username != null) {
            return username;
        } else {
            return "";
        }
    }

    /**
     * Method to get points.
     * @return the number of points they have.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Method to get saved.
     * @return the amount of co2 saved.
     */
    public double getSaved() {
        return saved;
    }
}
