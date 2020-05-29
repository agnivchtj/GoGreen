package client.Tests;

import client.messages.responses.CreateFriendResponse;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateFriendResponseTest {
    @Test
    public void testGetStatus() {
        CreateFriendResponse lr = new CreateFriendResponse("status");
        assertEquals("status", lr.getStatus());
    }

    @Test
    public void testSetStatus() {
        CreateFriendResponse lr = new CreateFriendResponse("status");
        lr.setStatus("status");
        assertEquals("status", lr.getStatus());
    }

    @Test
    public void testNullStatus(){
        CreateFriendResponse lr = new CreateFriendResponse(null);
        assertEquals("", lr.getStatus());
    }
}