package api;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the list of statistics for multiple users.
 */
public class Stats extends Response {

    /**
     * List containing all the statistics.
     */
    private List<Stat> stats;

    /**
     * Constructor Stats class.
     * @param st is a list containing Stat objects.
     */
    public Stats(final List<Stat> st) {
        super("Success");
        this.stats = st;
    }

    /**
     * Getter for the list of statistics.
     * @return a list with Stat objects.
     */
    public List<Stat> getStats() {
        if (this.stats != null) {
            return this.stats;
        } else {
            return new ArrayList<>();
        }
    }
}
