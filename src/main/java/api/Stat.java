package api;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the statistics for a certain user.
 */
public class Stat extends Response {

    /**
     * Username of the account.
     */
    private String username;

    /**
     * Points obtained till now.
     */
    private int points;

    /**
     * CO2 saved in kg.
     */
    private double saved;

    /**
     * List with recent activities done.
     */
    private List<Activity> recent;

    /**
     * Constructor for the stat class.
     * @param user is the username of the account owner.
     * @param po are the points obtained till now.
     * @param sav is the amount of co2 that has been saved in kg.
     * @param activities is a list containing the 10 most recent activities.
     */
    public Stat(final String user, final int po,
                final double sav, final List<Activity> activities) {
        super("Success");
        this.username = user;
        this.points = po;
        this.saved = sav;
        this.recent = activities;
    }

    /**
     * Getter for the username attribute.
     * @return a string containing the username.
     */
    public String getUsername() {
        if (this.username != null) {
            return this.username;
        } else {
            return "";
        }
    }

    /**
     * Getter for the points obtained.
     * @return an integer value with the amount of points.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Getter for the CO2 saved.
     * @return a float value with the amount saved.
     */
    public double getSaved() {
        return this.saved;
    }

    /**
     * Getter for the list of activities.
     * @return a list with all the most recent activities.
     */
    public List<Activity> getRecent() {
        if (this.recent != null) {
            return this.recent;
        } else {
            return new ArrayList<>();
        }
    }
}
