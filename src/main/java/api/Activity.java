package api;

/**
 * Class representing a single activity done by a user.
 */
public class Activity {

    /**
     * Type of the activity.
     */
    private String type;
    /**
     * Additional comment user added.
     */
    private String comment;
    /**
     * Date when the activity was done.
     */
    private String timestamp;

    /**
     * Number of points obtained during that activity.
     */
    private String points;

    /**
     * Constructor get a type,comment and timestamp.
     * @param typ is the type of activity done.
     * @param com is the additional comment by the user.
     * @param time is the date when the activity was added.
     * @param poi is the amount of points obtained.
     */
    public Activity(final String typ, final String com, final String time,
                    final String poi) {
        this.type = typ;
        this.comment = com;
        this.timestamp = time;
        this.points = poi;
    }

    /**
     * Getter for the activity type.
     * @return a string containing the type of activity.
     */
    public String getType() {
        if (this.type != null) {
            return this.type;
        } else {
            return "";
        }
    }

    /**
     * Getter for the activity comment.
     * @return a string containing the user comment.
     */
    public String getComment() {
        if (this.comment != null) {
            return this.comment;
        } else {
            return "";
        }
    }

    /**
     * Getter for the activity timestamp.
     * @return a string containing the date of the activity.
     */
    public String getTimestamp() {
        if (this.timestamp != null) {
            return this.timestamp;
        } else {
            return "";
        }
    }

    /**
     * Getter for the points of the single activity.
     * @return a string containing the number of points.
     */
    public String getPoints() {
        if (this.points != null) {
            return this.points;
        } else {
            return "";
        }
    }

}
