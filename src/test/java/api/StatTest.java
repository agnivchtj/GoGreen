package api;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class StatTest {
    
    @Test
    public void testNullConstructor(){
        Stat s = new Stat(null, 0, 0.0, new ArrayList<>());
        assertEquals("", s.getUsername());
    }
    
    @Test
    public void testNullList(){
        Stat s = new Stat(null, 0, 0.0, null);
        assertTrue(s.getRecent().size() == 0);
    }
    
    @Test
    public void testGetUsername() {
        Stat s = new Stat("test", 0, 0.0, new ArrayList<>());
        assertEquals("test", s.getUsername());
    }
    
    @Test
    public void testGetPoints(){
        Stat s = new Stat("test", 10, 1.2, new ArrayList<>());
        assertEquals(10, s.getPoints());
    }
    
    @Test
    public void testGetSaved(){
        Stat s = new Stat("test", 10, 1.2, new ArrayList<>());
        assertEquals(1.2, s.getSaved(), 0.1);
    }
    
    @Test
    public void testGetList(){
        Stat s = new Stat(null, 0, 0.0, new ArrayList<>());
        assertTrue(s.getRecent().size() == 0);
    }
}