package db;

import api.Activity;
import api.Friend;
import api.Stat;
import api.Stats;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class DatabaseQueryTest {

    private static DatabaseCreate db;

    @BeforeClass
    public static void beforeEverything(){
        db = new DatabaseCreate();
        db.initialize();
        Connection preDB = new DatabaseCreate().connect();

        String sql = "DELETE FROM users WHERE id IN (10000,10001)";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        String hashPass = new BCryptPasswordEncoder().encode("api_password");
        sql = "INSERT INTO users VALUES (10000, 'api_user', 'api_email', '" + hashPass + "' )";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        sql = "INSERT INTO users VALUES (10001, 'friend', 'friend_email', 'friend_password')";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        sql = "DELETE FROM friends WHERE user_id = 10000";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        sql = "DELETE FROM users WHERE username = 'correct_user_test'";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        sql = "INSERT INTO activities(user_id, type, comment) VALUES ('10000', 'veg meal', 'comment')";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }
        
        sql = "INSERT INTO users VALUES (10002, 'friend_test', 'friend_test_email', 'friend_test_pass')";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }
        
        sql = "INSERT INTO friends VALUES (10000,10002)";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            
        }
    }

    @AfterClass
    public static void end(){
        Connection preDB = new DatabaseCreate().connect();

        String sql = "DELETE FROM users WHERE id IN (10000,10001,10002)";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        sql = "DELETE FROM friends WHERE user_id = 10000";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        sql = "DELETE FROM users WHERE username = 'correct_user_test'";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }
    }

    @Test
    public void testConstructor(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertNotNull(dbquery.getConn());
    }

    @Test
    public void addAlreadyExistingUser(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertEquals(-1, dbquery.addNewUser("api_user", "api_email", "first_password"));
    }

    @Test
    public void testAuthIDCorrect(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertTrue(dbquery.authUser("id", "10000", "api_password"));
    }

    @Test
    public void testAuthIDIncorrect(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertFalse(dbquery.authUser("id", "10000", "incorrect_password"));
    }

    @Test
    public void testAuthEmailCorrect(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertTrue(dbquery.authUser("email","api_email", "api_password"));
    }

    @Test
    public void testAuthEmailIncorrect(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertFalse(dbquery.authUser("email", "api_email", "incorrect_password"));
    }

    @Test
    public void testAuthUsernameCorrect(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertTrue(dbquery.authUser("username", "api_user", "api_password"));
    }

    @Test
    public void testAuthUsernameIncorrect(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertFalse(dbquery.authUser("username", "api_user", "incorrect_password"));
    }

    @Test
    public void testIncorrectAddUser(){
        DatabaseQuery dbquery = new DatabaseQuery(null);
        assertEquals(-1, dbquery.addNewUser("test", "test", "test"));
    }

    @Test
    public void testAddUserActivity(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertTrue(dbquery.addNewUserActivity("10000", "Eat a vegetarian meal", "This is my first comment", new HashMap<>()));
    }

    @Test
    public void testAddFriends(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertTrue(dbquery.addFriend("10000", "10001"));
    }

    @Test
    public void testAddNullFriend(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertFalse(dbquery.addFriend("10000", null));
    }

    @Test
    public void testAddFriendNull(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertFalse(dbquery.addFriend(null, "10001"));
    }

    @Test
    public void testGetNonExistentUserActivities(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertNull(dbquery.getUserActivities(null));
    }

    @Test
    public void testGetUserActivities(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertNotNull(dbquery.getUserActivities("api_user"));
    }

    @Test
    public void testGetNonExistentUserFriends(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertNull(dbquery.getUserFriends(null));
    }

    @Test
    public void testGetUserFriends(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertNotNull(dbquery.getUserFriends("10000"));
    }
    
    @Test
    public void testGetStatNonExistentUser(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertNull(dbquery.getStat("non_existent_user", null));
    }
    
    @Test
    public void testGetStatUser(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        Stat s = dbquery.getStat("api_user", "10000");
        assertEquals("api_user", s.getUsername());   
    }

    @Test
    public void testGetStatsNonExistentUser(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        assertNull(dbquery.getStats("non_existent_user", null));
    }

    @Test
    public void testGetStatsUser(){
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        Stats s = dbquery.getStats("api_user", "10000");
        assertNotNull(s.getStats());
    }

}