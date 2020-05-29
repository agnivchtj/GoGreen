package client.Tests;

import client.messages.requests.CreateFriendRequest;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CreateFriendRequestTest {
    @Test
    public void testGetUsername() {
        CreateFriendRequest lr = new CreateFriendRequest("username", "friend");
        assertEquals("username", lr.getUsername());
    }

    @Test
    public void testGetFriend() {
        CreateFriendRequest lr = new CreateFriendRequest("username", "friend");
        assertEquals("friend", lr.getFriend());
    }

    @Test
    public void testSetUsername() {
        CreateFriendRequest lr = new CreateFriendRequest("username", "friend");
        lr.setUsername("username");
        assertEquals("username", lr.getUsername());
    }

    @Test
    public void testSetFriend() {
        CreateFriendRequest lr = new CreateFriendRequest("username", "friend");
        lr.setFriend("type");
        assertEquals("type", lr.getFriend());
    }

    @Test
    public void testEmptyConstructor(){
        CreateFriendRequest lr = new CreateFriendRequest();
        assertNull(lr.getFriend());
        assertNull(lr.getUsername());
    }
}
