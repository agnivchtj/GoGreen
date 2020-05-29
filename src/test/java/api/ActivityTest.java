package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class ActivityTest {

    @Test
    public void testConstructor(){
        Activity a = new Activity(null, null, null, null);
        assertEquals("", a.getComment());
    }

    @Test
    public void testNullType(){
        Activity a = new Activity(null, null, null, null);
        assertEquals("", a.getType());
    }

    @Test
    public void testNullTimestamp(){
        Activity a = new Activity(null, null, null, null);
        assertEquals("", a.getTimestamp());
    }

    @Test
    public void testNullComment(){
        Activity a = new Activity(null, null, null, null);
        assertEquals("", a.getComment());
    }

    @Test
    public void testNullPoints(){
        Activity a = new Activity(null, null, null, null);
        assertEquals("", a.getPoints());
    }

    @Test
    public void testGetType(){
        Activity a = new Activity("type", "comnent", "date", "0");
        assertEquals("type", a.getType());
    }

    @Test
    public void testGetComment(){
        Activity a = new Activity("type", "comment", "date", "0");
        assertEquals("comment", a.getComment());
    }

    @Test
    public void testGetDate(){
        Activity a = new Activity("type", "comment", "date", "0");
        assertEquals("date", a.getTimestamp());
    }

    @Test
    public void testGetPoints(){
        Activity a = new Activity("type", "comment", "date", "0.0");
        assertEquals("0.0", a.getPoints());
    }

}