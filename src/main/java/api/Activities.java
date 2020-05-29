package api;

import java.util.List;

/**
 * Class representing the activities done by a user.
 */
public class Activities extends Response {

    /**
     * List containing all the activities.
     */
    private List<Activity> activities;

    /**
     * Constructor get a list containing Activity objects.
     * @param act is a List with all the activities.
     */
    public Activities(final List<Activity> act) {
        super("Success");
        this.activities = act;
    }

    /**
     * Getter for the list of activities.
     * @return a list containing the Activity objects.
     */
    public List<Activity> getActivities() {

        if (this.activities != null) {
            return this.activities;
        } else {
            return null;
        }
    }

}
