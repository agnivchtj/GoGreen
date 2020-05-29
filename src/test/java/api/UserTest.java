package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void testgetId(){
        User u = new User("Success", 1);
        assertEquals(1, u.getId());
    }

}