package db;

import api.Activity;
import api.Friend;
import api.Stat;
import api.Stats;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * DatabaseQuery class which connects to the database and run prepared queries.
 */
public class DatabaseQuery {

    /**
     * First parameter of a SQL query.
     */
    private static final int FIRST_QUERY_PARAM = 1;

    /**
     * Second parameter of a SQL query.
     */
    private static final int SECOND_QUERY_PARAM = 2;

    /**
     * Third parameter of a SQL query.
     */
    private static final int THIRD_QUERY_PARAM = 3;

    /**
     * Fourth parameter of a SQL query.
     */
    private static final int FOURTH_QUERY_PARAM = 4;

    /**
     * Fifth parameter of a SQL query.
     */
    private static final int FIFTH_QUERY_PARAM = 5;

    /**
     * Amount of KG/year per point given.
     */
    private static final int KG_SAVED_POINTS = 50;

    /**
     * Days in a year.
     */
    private static final int DAYS_YEAR = 365;

    /**
     * Recent activities to display in stats.
     */
    private static final int RECENT_ACTIVITIES = 10;

    /**
     * Connection object used to connect to the database.
     */
    private Connection conn;

    /**
     * Constructor which takes the connection and saves it.
     * @param connObj Connection object generated from the DatabaseCreate class.
     */
    public DatabaseQuery(final Connection connObj) {
        this.conn = connObj;
    }

    /**
     * Method to add a new user to the database.
     * @param username of the new user, must be unique.
     * @param email of the new user, must be unique.
     * @param password of the new user.
     * @return the id of the user in the database, -1 if error.
     */
    public int addNewUser(final String username,
                          final String email, final String password) {
        if (this.conn != null) {
            return addNewUser(username, email, password, true);
        } else {
            return -1;
        }
    }

    /**
     * Private method to add a new user.
     * @param username of the new user, must be unique.
     * @param email of the new user, must be unique.
     * @param password of the new user.
     * @param valid added parameter for the private method.
     * @return the id of the user in the database, -1 if error.
     */
    public int addNewUser(final String username, final String email,
                           final  String password, final boolean valid) {

        String sql = "INSERT INTO users(username,email,password) "
                + "VALUES (?, ?, ?)";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, username);
            pstmt.setString(SECOND_QUERY_PARAM, email);
            pstmt.setString(THIRD_QUERY_PARAM, password);
            pstmt.execute();
        } catch (SQLException e) {
            return -1;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return -1;
            }
        }

        String id = getIdFromUsername(username);
        if (id != null) {
            return Integer.parseInt(id);
        } else {
            return -1;
        }
    }

    /**
     * Authenticate user see API Docs for details.
     * @param method either id / username / email.
     * @param identity based on chosen method.
     * @param password password of the user.
     * @return true if successful.
     */
    public boolean authUser(final String method,
                            final String identity, final String password) {
        String sql = "SELECT password FROM users WHERE " + method + " = ?";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, identity);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new BCryptPasswordEncoder().matches(password,
                        rs.getString("password"));
            }
            return false;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return false;
            }
        }
    }

    /**
     * Get the id of username given the username.
     * @param username string containing a in a table.
     * @return the id of the user.
     */
    public String getIdFromUsername(final String username) {

        String sql = "SELECT id FROM users WHERE username = ?";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return null;
            }
        }
    }

    /**
     * Add to the database a new activity the user has done.
     * @param idUsername of the user who did the activity.
     * @param type is the activity done by the user, ex: Eat a vegetarian meal.
     * @param comment additional comments the user had on the activity.
     * @param saved is the map with the activity -> points values.
     * @return true if it is successful.
     */
    public boolean addNewUserActivity(final String idUsername,
                                      final String type, final String comment,
                                      final Map<String, Integer> saved) {

        String sql = "INSERT INTO activities(user_id, type, comment, saved)"
                + " VALUES (?, ?, ?, ?)";

        //String idUsername = getIdFromUsername(username);

        int save = 0;
        if (saved.get(type) != null) {
            save = saved.get(type);
        }

        if (idUsername == null) {
            return false;
        }

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, idUsername);
            pstmt.setString(SECOND_QUERY_PARAM, type);
            pstmt.setString(THIRD_QUERY_PARAM, comment);
            pstmt.setInt(FOURTH_QUERY_PARAM, save);
            pstmt.executeUpdate();
            deleteActivityDuplicates();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return false;
            }
        }
    }

    /**
     * Delete duplicated activities if found.
     */
    public void deleteActivityDuplicates() {

        String sql = "DELETE FROM activities WHERE"
                + " EXISTS ( SELECT 1 FROM activities a2"
                + " WHERE activities.date = a2.date"
                + " AND activities.id + 1 = a2.id )";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a new tuple in the database representing the friendship.
     * @param idUsername username of the person making the request.
     * @param idFriend username of the friend to add.
     * @return true if succesful.
     */
    public boolean addFriend(final String idUsername, final String idFriend) {

        String sql = "INSERT INTO friends VALUES (?, ?)";

        //String idUsername = getIdFromUsername(username);
        //String idFriend = getIdFromUsername(friend);

        if (idFriend == null || idUsername == null) {
            return false;
        }

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, idUsername);
            pstmt.setString(SECOND_QUERY_PARAM, idFriend);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return false;
            }
        }
    }

    /**
     * Get all the activities done by a certain user.
     * @param userid is a string containing the username.
     * @return a list of all the activities.
     */
    public List<Activity> getUserActivities(final String userid) {

        String sql = "SELECT type, comment, date, saved"
                + " FROM activities"
                + " WHERE user_id = ? "
                + " ORDER BY date DESC";

        //String userid = getIdFromUsername(username);

        if (userid == null) {
            return null;
        }

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, userid);
            ResultSet rs = pstmt.executeQuery();
            List<Activity> res = new ArrayList<>();
            while (rs.next()) {
                res.add(new Activity(rs.getString("type"),
                        rs.getString("comment"), rs.getString("date"),
                        rs.getString("saved")));
            }
            return res;
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return null;
            }
        }
    }

    /**
     * Get the friends of a user as a list.
     * @param userid is a string with username.
     * @return a list containing all the friends of the username.
     */
    public List<Friend> getUserFriends(final String userid) {

        String sql = "SELECT username FROM friends"
                + " JOIN users ON friends.friend_id = users.id"
                + " WHERE user_id = ?";

        //String userid = getIdFromUsername(username);

        if (userid == null) {
            return null;
        }

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, userid);
            ResultSet rs = pstmt.executeQuery();
            List<Friend> res = new ArrayList<>();
            while (rs.next()) {
                res.add(new Friend(rs.getString("username")));
            }
            return res;
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return null;
            }
        }
    }

    /**
     * Method to get the statistics of a singel user.
     * @param username is the respective username.
     * @param idUsername is the username id.
     * @return an object Stat with all the statistics.
     */
    public Stat getStat(final String username, final String idUsername) {

        int points = 0;

        //String idUsername = getIdFromUsername(username);
        if (idUsername == null) {
            return null;
        }

        String sql = "SELECT sum(saved) AS points "
                + "FROM activities WHERE user_id = ?";

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(FIRST_QUERY_PARAM, idUsername);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                points = rs.getInt("points");
            }
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException | NullPointerException e) {
                return null;
            }
        }

        List<Activity> rec = getUserActivities(idUsername);
        return new Stat(username, points,
                (double) points * KG_SAVED_POINTS / DAYS_YEAR,
                rec.subList(0, Math.min(RECENT_ACTIVITIES, rec.size())));
    }

    /**
     * Method to get the statistics for a certain user and his friends.
     * @param username is the respective username.
     * @param userid is the username id.
     * @return a Stats object containing a List with statistics.
     */
    public Stats getStats(final String username, final String userid) {
        List<Friend> friends = getUserFriends(userid);
        if (friends == null) {
            return null;
        }

        List<Stat> res = new ArrayList<>();
        res.add(getStat(username, getIdFromUsername(username)));
        for (Friend f: friends) {
            res.add(getStat(
                    f.getUsername(), getIdFromUsername(f.getUsername())));
        }
        return new Stats(res);
    }

    /**
     * Getter for the connection to the database.
     * @return a Connection object which allows to send queries to the database.
     */
    public Connection getConn() {
        return this.conn;
    }

}
