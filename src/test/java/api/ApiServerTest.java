package api;

import db.DatabaseCreate;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ApiServerTest {

    private static ApiServer api;

    @Mock
    private DatabaseCreate dbMock;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void set(){
        api = new ApiServer();
        api.main(new String[0]);
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

        sql = "DELETE FROM users WHERE username = 'client_user_test'";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }
    }

    @AfterClass
    public static void end(){
        api.stop();

        Connection preDB = new DatabaseCreate().connect();

        String sql = "DELETE FROM users WHERE id IN (10000,10001)";
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

        sql = "DELETE FROM users WHERE username = 'client_user_test'";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }
    }

    @Test
    public void testDefaultReq(){
        Error res = (Error) api.defaultReq();
        assertEquals("Error", res.getStatus());
        assertEquals("Specified path is not correct", res.getDetails());
    }

    @Test
    public void testUsernameNotValid(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.createNewUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }

    @Test
    public void testPasswordNotvalid(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "test");
        Error res = (Error) api.createNewUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Password is not valid", res.getDetails());
    }

    @Test
    public void testEmailNotValid(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "test");
        req.put("password", "test");
        Error res = (Error) api.createNewUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Email is not valid", res.getDetails());
    }

    @Test
    public void testAlreadyRegisteredUser(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "api_user");
        req.put("email", "api_email");
        req.put("password", "random_pass");
        Error res = (Error) api.createNewUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Can't insert into database", res.getDetails());
    }

    @Test
    public void testAuthMethodNotValid(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.authUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Method is not valid", res.getDetails());
    }

    @Test
    public void testAuthPasswordNotValid(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "random_method");
        Error res = (Error) api.authUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Password is not valid", res.getDetails());
    }

    @Test
    public void testAuthIdentityNotValid(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "random_method");
        req.put("password", "random_password");
        Error res = (Error) api.authUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Identity is not valid", res.getDetails());
    }

    @Test
    public void testInvalidAuthMethod(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "random_method");
        req.put("password", "random_password");
        req.put("identity", "random_identity");
        Error res = (Error) api.authUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Method is not valid", res.getDetails());
    }

    @Test
    public void testAuthIdMethodCorrect(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "id_only");
        req.put("password", "api_password");
        req.put("identity", "10000");
        Response res = api.authUser(req);
        assertEquals("Success", res.getStatus());
    }

    @Test
    public void testAuthUsernameMethodCorrect(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "username_only");
        req.put("password", "api_password");
        req.put("identity", "api_user");
        Response res = api.authUser(req);
        assertEquals("Success", res.getStatus());
    }

    @Test
    public void testAuthEmailMethodCorrect(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "email_only");
        req.put("password", "api_password");
        req.put("identity", "api_email");
        Response res = api.authUser(req);
        assertEquals("Success", res.getStatus());
    }

    @Test
    public void testAuthIdMethodInCorrect(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "id_only");
        req.put("password", "incorrect_password");
        req.put("identity", "10000");
        Error res = (Error) api.authUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Invalid Credentials", res.getDetails());
    }

    @Test
    public void testAuthUsernameMethodInCorrect(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "username_only");
        req.put("password", "incorrect_password");
        req.put("identity", "api_user");
        Error res = (Error) api.authUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Invalid Credentials", res.getDetails());
    }

    @Test
    public void testAuthEmailMethodInCorrect(){
        Map<String, Object> req = new HashMap<>();
        req.put("method", "email_only");
        req.put("password", "incorrect_password");
        req.put("identity", "api_email");
        Error res = (Error) api.authUser(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Invalid Credentials", res.getDetails());
    }

    @Test
    public void testAddCorrectUser(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "correct_user_test");
        req.put("password", "correct_password_test");
        req.put("email", "correct_email_test");
        User res = (User)  api.createNewUser(req);
        assertEquals("Success", res.getStatus());
        assertTrue(res.getId()>0);
    }

    @Test
    public void testMockFailInitialize() {
        try{
            api.stop();
            when(dbMock.initialize()).thenReturn(false);
            api.main(new String[0]);
            assertTrue(true);
        }catch(Exception e){
            fail();
        }
    }

    @Test
    public void testAddActivity(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "api_user");
        req.put("type", "veg meal");
        req.put("comment", "random comment");
        Response res = api.createNewActivity(req);
        assertEquals("Added", res.getStatus());
    }

    @Test
    public void testActivityUsernameNotvalid(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.createNewActivity(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }

    @Test
    public void testActivityTypeNotvalid(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "test");
        Error res = (Error) api.createNewActivity(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Type is not valid", res.getDetails());
    }

    @Test
    public void testActivityCommentNotvalid(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "test");
        req.put("type", "test");
        Error res = (Error) api.createNewActivity(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Comment is not valid", res.getDetails());
    }

    @Test
    public void testAddFriend(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "api_user");
        req.put("friend", "friend");
        Response res = api.createNewFriend(req);
        assertEquals("Added", res.getStatus());
    }

    @Test
    public void testFriendUsernameNotvalid(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.createNewFriend(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }

    @Test
    public void testFriendNotvalid(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "test");
        Error res = (Error) api.createNewFriend(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Friend is not valid", res.getDetails());
    }

    @Test
    public void testActivityNotInDatabase(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "username_not_in_database");
        req.put("type", "type_activity");
        req.put("comment", "some_comment");
        Error res = (Error) api.createNewActivity(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Can't insert into database", res.getDetails());
    }

    @Test
    public void testGetActivityNullUser(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.getActivities(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }

    @Test
    public void testGetActivityNotInDatabase(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "not_in_database");
        Error res = (Error) api.getActivities(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }

    @Test
    public void testGetActivities(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "api_user");
        Activities res = (Activities) api.getActivities(req);
        assertEquals("Success", res.getStatus());
        assertNotNull(res.getActivities());
    }

    @Test
    public void testGetNullFriend(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.getFriends(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }

    @Test
    public void testGetFriends(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "api_user");
        Friends res = (Friends) api.getFriends(req);
        assertEquals("Success", res.getStatus());
    }

    @Test
    public void testCreateNoExistentFriend(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "first_username");
        req.put("friend", "not_a_real_user");
        Error res = (Error) api.createNewFriend(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Can't insert into database", res.getDetails());
    }

    @Test
    public void testGetNonExistentUserFriends(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "this_doesnt_exists_in_database");
        Error res = (Error) api.getFriends(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }
    
    @Test
    public void testGetStatNullUser(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.getStat(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }

    @Test
    public void testGetStatsNullUser(){
        Map<String, Object> req = new HashMap<>();
        Error res = (Error) api.getStats(req);
        assertEquals("Error", res.getStatus());
        assertEquals("Username is not valid", res.getDetails());
    }
    
    @Test
    public void testGetStatUser(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "api_user");
        Stat res = (Stat) api.getStat(req);
        assertEquals("Success", res.getStatus());
        assertEquals("api_user", res.getUsername());
    }
    
    @Test
    public void testGetStatsUser() {
        Map<String, Object> req = new HashMap<>();
        req.put("username", "api_user");
        Stats res = (Stats) api.getStats(req);
        assertEquals("Success", res.getStatus());
        assertNotNull(res.getStats());
    }
    
    @Test
    public void testGetStatNonExistentUser(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "non_existent_user");
        Error res = (Error) api.getStat(req);
        assertEquals("Error", res.getStatus());
    }

    @Test
    public void testGetStatsNonExistentUser(){
        Map<String, Object> req = new HashMap<>();
        req.put("username", "non_existent_user");
        Error res = (Error) api.getStats(req);
        assertEquals("Error", res.getStatus());
    }

}