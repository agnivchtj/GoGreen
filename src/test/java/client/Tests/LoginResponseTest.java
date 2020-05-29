package client.Tests;

import client.LoginResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginResponseTest {
    @Test
    public void testGetStatus() {
        LoginResponse lr = new LoginResponse("status", "details");
        assertEquals("status", lr.getStatus());
    }

    @Test
    public void testGetDetails() {
        LoginResponse lr = new LoginResponse("status", "details");
        assertEquals("details", lr.getDetails());
    }

    @Test
    public void testSetStatus() {
        LoginResponse lr = new LoginResponse("status", "details");
        lr.setStatus("status");
        assertEquals("status", lr.getStatus());
    }

    @Test
    public void testSetDetails() {
        LoginResponse lr = new LoginResponse("status", "details");
        lr.setDetails("details");
        assertEquals("details", lr.getDetails());
    }
}