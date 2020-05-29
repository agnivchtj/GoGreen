package api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ActivitiesTest {

    @Test
    public void testContructor(){
        Activity a = new Activity("type", "comment", "date", "0");
        List<Activity> l = new ArrayList<>();
        l.add(a);
        Activities ac = new Activities(l);
        assertTrue(ac.getActivities().equals(l));
    }

    @Test
    public void testGetter(){
        Activity a = new Activity("type", "comment", "date", "0");
        List<Activity> l = new ArrayList<>();
        l.add(a);
        Activities ac = new Activities(l);

        List<Activity> newl = ac.getActivities();
        assertEquals("type", newl.get(0).getType());
        assertEquals("comment", newl.get(0).getComment());
        assertEquals("date", newl.get(0).getTimestamp());
    }

    @Test
    public void testNullConstructor(){
        Activities ac = new Activities(null);
        assertNull(ac.getActivities());
    }

}