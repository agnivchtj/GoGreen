package client.Tests;

import client.RegistrationResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationResponseTest {
    @Test
    public void testGetStatus() {
        RegistrationResponse lr = new RegistrationResponse("status",0);
        assertEquals("status", lr.getStatus());
    }

    @Test
    public void testGetID() {
        RegistrationResponse lr = new RegistrationResponse("status",0);
        assertEquals(0, lr.getId());
    }
    
    @Test
    public void testGetDetailts(){
        RegistrationResponse lr = new RegistrationResponse("status",0);
        lr.setDetails("details");
        assertEquals("details", lr.getDetails());
    }

    @Test
    public void testSetDetailts(){
        RegistrationResponse lr = new RegistrationResponse("status",0);
        lr.setDetails("details");
        lr.setDetails("details2");
        assertEquals("details2", lr.getDetails());
    }
}