package client.Tests;

import client.RegistrationRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationRequestTest {
    @Test
    public void testGetUsername() {
        RegistrationRequest lr = new RegistrationRequest("username", "email", "password");
        assertEquals("username", lr.getUsername());
    }

    @Test
    public void testGetEmail() {
        RegistrationRequest lr = new RegistrationRequest("username", "email", "password");
        assertEquals("email", lr.getEmail());
    }

    @Test
    public void testGetPassword() {
        RegistrationRequest lr = new RegistrationRequest("username", "email", "password");
        assertEquals("password", lr.getPassword());
    }

    @Test
    public void testSetUsername() {
        RegistrationRequest lr = new RegistrationRequest("username", "email", "password");
        lr.setUsername("username");
        assertEquals("username", lr.getUsername());
    }

    @Test
    public void testSetEmail() {
        RegistrationRequest lr = new RegistrationRequest("username", "email", "password");
        lr.setEmail("email");
        assertEquals("email", lr.getEmail());
    }

    @Test
    public void testSetPassword() {
        RegistrationRequest lr = new RegistrationRequest("username", "email", "password");
        lr.setPassword("password");
        assertEquals("password", lr.getPassword());
    }
}
