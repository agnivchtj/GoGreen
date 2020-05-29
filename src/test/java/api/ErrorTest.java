package api;

import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorTest {

    @Test
    public void testStandardError() {
        Error e = new Error();
        assertEquals("Incorrect Request" , e.getDetails());
    }

    @Test
    public void testCustomError(){
        Error e = new Error("Custom error message");
        assertEquals("Custom error message", e.getDetails());
    }

    @Test
    public void testNullError(){
        Error e = new Error(null);
        assertEquals("", e.getDetails());
    }
}