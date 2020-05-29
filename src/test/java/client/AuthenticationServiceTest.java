package client;

import api.ApiServer;
import db.DatabaseCreate;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    private static ApiServer api;

    @BeforeClass
    public static void set(){
        api = new ApiServer();
        api.main(new String[0]);

        Connection preDB = new DatabaseCreate().connect();

        String sql = "DELETE FROM users WHERE username IN ('tes','client_user')";
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
    }

    @AfterClass
    public static void end(){
        api.stop();
    }

    @Test
    public void testAuthRequest(){
        String hashPass = new BCryptPasswordEncoder().encode("client_password");
        assertTrue(AuthenticationService.register("client_user", "client_email", hashPass));
    }

    @Test
    public void test(){
        assertTrue(AuthenticationService.signIn("tes", "tes"));
    }

}