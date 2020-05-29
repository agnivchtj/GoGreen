package api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FriendsTest {

    @Test
    public void testConstructor(){
        Friends fs = new Friends(null);
        assertNull(fs.getFriends());
    }

    @Test
    public void testGetter(){
        Friend f = new Friend("friend");
        List<Friend> l = new ArrayList<>();
        l.add(f);
        Friends fs = new Friends(l);
        assertTrue(fs.getFriends().equals(l));
    }

}