package db;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class to initialize the SQLite database.
 */
public class DatabaseCreate {

    /**
     * Number of characters to check if db variable is correct.
     */
    private static final int DB_CHARACTERS = 3;

    /**
     * Map containing the name of the activity with the respective saved co2.
     */
    private static Map<String, Integer> activities;

    /**
     * Base address for the SQLite database.
     */
    private String url = "jdbc:sqlite:files\\db\\";

    /**
     * Datasource to access SQLite database.
     */
    private SQLiteDataSource ds;

    /**
     * Default constructor to initialize the database.
     */
    public DatabaseCreate() {
        this.url += "main.db";
        activities = readSaved();
        ds = new SQLiteDataSource();
        ds.setUrl(this.url);
    }

    /**
     * Custom database constructor.
     * @param name of the SQLite database.
     */
    public DatabaseCreate(final String name) {
        if (name.substring(name.length() - DB_CHARACTERS, name.length())
                .equals(".db")) {
            this.url += name;
        } else {
            this.url += "main.db";
        }
        activities = readSaved();
        ds = new SQLiteDataSource();
        ds.setUrl(this.url);
    }

    /**
     * Initialize the database and the tables.
     * @return true if successful.
     */
    public boolean initialize() {
        return createDatabase() && createUsersTable()
                && createActivitesTable() && createFriendsTable();
//        if (createDatabase()) {
//            if (createUsersTable()
//                    && createActivitesTable()
//                    && createFriendsTable()) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
    }

    /**
     * Create the Database if it is not already present.
     * @return true if successful.
     */
    public boolean createDatabase() {
        // Fixing No Suitable Driver found bug
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            return false;
        }
        //

        try {
            return ds.getConnection() != null;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Create the table for the users if not existent.
     * @return true if successful.
     */
    public boolean createUsersTable() {

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users(\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT, \n"
                + " username VARCHAR(128) NOT NULL UNIQUE, \n"
                + " email VARCHAR(128) NOT NULL UNIQUE, \n"
                + " password VARCHAR(128) NOT NULL \n"
                + ");";

        Statement stmt = null;
        try {
            Connection conn = ds.getConnection();
            stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException | NullPointerException e) {
                return false;
            }
        }
    }

    /**
     * Create the table for the activities if not existent.
     * @return true if successful.
     */
    public boolean createActivitesTable() {

        String sql = "CREATE TABLE IF NOT EXISTS activities(\n"
                + "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "    user_id VARCHAR(128) NOT NULL,\n"
                + "    type VARCHAR(128) NOT NULL,\n"
                + "    comment VARCHAR(1024),\n"
                + "    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n"
                + "    saved INTEGER DEFAULT 0,\n"
                + "    FOREIGN KEY (user_id) REFERENCES users(id)\n"
                + ")";

        Statement stmt = null;
        try {
            Connection conn = ds.getConnection();
            stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException | NullPointerException e) {
                return false;
            }
        }
    }

    /**
     * Create the table for user friendships.
     * @return true if succesful.
     */
    public boolean createFriendsTable() {

        String sql = "CREATE TABLE IF NOT EXISTS friends(\n"
                + "    user_id INT NOT NULL,\n"
                + "    friend_id INT NOT NULL,\n"
                + "    PRIMARY KEY(user_id, friend_id)\n"
                + ")";

        Statement stmt = null;
        try {
            Connection conn = ds.getConnection();
            stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                stmt.close();
            } catch (SQLException | NullPointerException e) {
                return false;
            }
        }
    }

    /**
     * Create a connection to the database that can be used to run queries.
     * @return Connection object to be used for the queries.
     */
    public Connection connect() {
        Connection conn = null;
        try {
            conn = ds.getConnection();
        } catch (SQLException e) {
            return null;
        }
        return conn;
    }

    /**
     * This class will read from a file the configuration for each activity.
     * @return a map containing an activity and the amount of points given.
     */
    public static Map<String, Integer> readSaved() {
        Map<String, Integer> act = new HashMap<>();
        try {
            Scanner sc = new Scanner(new File("files/config/activities.txt"));
            while (sc.hasNextLine()) {
                String[] ag = sc.nextLine().split(":");
                act.put(ag[0], Integer.parseInt(ag[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return act;
    }

    /**
     * Getter for the map of activity -> points.
     * @return a map containing an activity and the amount of points given.
     */
    public Map<String, Integer> getSaved() {
        if (activities == null) {
            return readSaved();
        } else {
            return activities;
        }
    }

    /**
     * Set a new map for the activities.
     * @param act is the map with all the activities.
     */
    public void setActivities(final Map<String, Integer> act) {
        activities = act;
    }

    /**
     * Getter for the url position of the database.
     * @return a string containing the url of the database.
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Set a new data source for the database.
     * @param dss is a SQLiteDatasource object.
     */
    public void setDs(final SQLiteDataSource dss) {
        this.ds = dss;
    }

}
