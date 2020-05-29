package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void testgetStatus(){
        Response r = new Response("Success");
        assertEquals("Success", r.getStatus());
    }

    @Test
    public void testgetNullStatus(){
        Response r = new Response(null);
        assertEquals("", r.getStatus());
    }


}