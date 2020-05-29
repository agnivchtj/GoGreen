package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendTest {

    @Test
    public void testConstructor(){
        Friend f = new Friend(null);
        assertEquals("", f.getUsername());
    }

    @Test
    public void testGetUsername(){
        Friend f = new Friend("friend");
        assertEquals("friend", f.getUsername());
    }

}