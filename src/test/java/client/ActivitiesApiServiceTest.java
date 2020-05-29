package client;

import api.ApiServer;
import api.Stat;
import db.DatabaseCreate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ActivitiesApiServiceTest {

    private static ApiServer api;

    @BeforeClass
    public static void set(){
        api = new ApiServer();
        api.main(new String[0]);

        Connection preDB = new DatabaseCreate().connect();

        String sql = "DELETE FROM users WHERE username IN ('tes')";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        sql = "DELETE FROM friends WHERE user_id = 9999";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        String hashPass = new BCryptPasswordEncoder().encode("tes");
        sql = "INSERT INTO users VALUES (9999, 'tes', 'tes', '" + hashPass + "' )";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        hashPass = new BCryptPasswordEncoder().encode("tom");
        sql = "INSERT INTO users VALUES (9998, 'tom', 'tom', '" + hashPass + "' )";
        try (PreparedStatement pstmt = preDB.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {

        }

        Map<String, Object> req = new HashMap<>();
        req.put("username", "tes");
        req.put("type", "sometype");
        req.put("comment", "comment");
        api.createNewActivity(req);
    }

    @AfterClass
    public static void end(){
        api.stop();
    }

    @Test
    public void testAddActivity(){
        assertTrue(ActivitiesApiService.createActivity("tes", "veg meal", "comment"));
    }

    @Test
    public void testGetActivity(){
        assertTrue(ActivitiesApiService.getActivity("tes").size() > 0);
    }

    @Test
    public void testActivityNullUsername(){
        assertNull(ActivitiesApiService.getActivity(null));
    }

    @Test
    public void testActivityNonExistentUser(){
        assertNull(ActivitiesApiService.getActivity("not_a_user"));
    }

    @Test
    public void testAddFriend() {
        assertTrue(ActivitiesApiService.createFriend("tes", "tom"));
    }

    @Test
    public void testGetFriend() {
        assertTrue(ActivitiesApiService.getFriend("tes").size() > 0);
    }

    @Test
    public void testFriendNullUsername() {
        assertNull(ActivitiesApiService.getFriend(null));
    }

    @Test
    public void testFriendNonExistentUser() {
        assertNull(ActivitiesApiService.getFriend("not_a_user"));
    }

    @Test
    public void testGetStat() throws IOException {
        Stat res = ActivitiesApiService.getStat("tes");
        assertEquals("tes", res.getUsername());
    }

    @Test
    public void testStatNullUsername() {
        assertNull(ActivitiesApiService.getStat(null));
    }

    @Test
    public void testStatNonExistentUser() {
        assertNull(ActivitiesApiService.getStat("not_a_user"));
    }

    @Test
    public void testGetStats() {
        assertTrue(ActivitiesApiService.getStats("tes").size() > 0);
    }

    @Test
    public void testStatsNullUsername() {
        assertNull(ActivitiesApiService.getStats(null));
    }

    @Test
    public void testStatsNonExistentUser() {
        assertNull(ActivitiesApiService.getStats("not_a_user"));
    }
}