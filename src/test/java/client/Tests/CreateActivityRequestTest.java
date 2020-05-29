package client.Tests;

import client.messages.requests.CreateActivityRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateActivityRequestTest {
    @Test
    public void testGetUsername() {
        CreateActivityRequest lr = new CreateActivityRequest("username", "type", "comment");
        assertEquals("username", lr.getUsername());
    }

    @Test
    public void testGetType() {
        CreateActivityRequest lr = new CreateActivityRequest("username", "type", "comment");
        assertEquals("type", lr.getType());
    }

    @Test
    public void testGetComment() {
        CreateActivityRequest lr = new CreateActivityRequest("username", "type", "comment");
        assertEquals("comment", lr.getComment());
    }

    @Test
    public void testSetUsername() {
        CreateActivityRequest lr = new CreateActivityRequest("username", "type", "comment");
        lr.setUsername("username");
        assertEquals("username", lr.getUsername());
    }

    @Test
    public void testSetType() {
        CreateActivityRequest lr = new CreateActivityRequest("username", "type", "comment");
        lr.setType("type");
        assertEquals("type", lr.getType());
    }

    @Test
    public void testSetComment() {
        CreateActivityRequest lr = new CreateActivityRequest("username", "type", "comment");
        lr.setComment("comment");
        assertEquals("comment", lr.getComment());
    }
    
    @Test
    public void testEmptyConstructor(){
        CreateActivityRequest lr = new CreateActivityRequest();
        assertNull(lr.getComment());
        assertNull(lr.getType());
        assertNull(lr.getUsername());
    }
}