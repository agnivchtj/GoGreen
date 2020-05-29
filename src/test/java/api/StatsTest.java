package api;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StatsTest {
    
    @Test
    public void testConstructor(){
        Stats st = new Stats(null);
        assertTrue(st.getStats().size() == 0);
    }
    
    @Test
    public void testGetStats(){
        Stats st = new Stats(new ArrayList<>());
        assertTrue(st.getStats().size() == 0);
    }

}