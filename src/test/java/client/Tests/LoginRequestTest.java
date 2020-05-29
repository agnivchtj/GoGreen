package client.Tests;

import client.LoginRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginRequestTest {
    @Test
    public void testGetMethod() {
        LoginRequest lr = new LoginRequest("method", "identity", "password");
        assertEquals("method", lr.getMethod());
    }

    @Test
    public void testGetIdentity() {
        LoginRequest lr = new LoginRequest("method", "identity", "password");
        assertEquals("identity", lr.getIdentity());
    }

    @Test
    public void testGetPassword() {
        LoginRequest lr = new LoginRequest("method", "identity", "password");
        assertEquals("password", lr.getPassword());
    }

    @Test
    public void testSetMethod() {
        LoginRequest lr = new LoginRequest("method", "identity", "password");
        lr.setMethod("method");
        assertEquals("method", lr.getMethod());
    }

    @Test
    public void testSetIdentity() {
        LoginRequest lr = new LoginRequest("method", "identity", "password");
        lr.setIdentity("identity");
        assertEquals("identity", lr.getIdentity());
    }

    @Test
    public void testSetPassword() {
        LoginRequest lr = new LoginRequest("method", "identity", "password");
        lr.setPassword("password");
        assertEquals("password", lr.getPassword());
    }
}