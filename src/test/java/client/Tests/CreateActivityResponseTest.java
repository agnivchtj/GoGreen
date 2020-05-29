package client.Tests;

import client.messages.responses.CreateActivityResponse;
import org.junit.Test;
import static org.junit.Assert.*;

public class CreateActivityResponseTest {
    @Test
    public void testGetStatus() {
        CreateActivityResponse lr = new CreateActivityResponse("status");
        assertEquals("status", lr.getStatus());
    }

    @Test
    public void testSetStatus() {
        CreateActivityResponse lr = new CreateActivityResponse("status");
        lr.setStatus("status");
        assertEquals("status", lr.getStatus());
    }
    
    @Test
    public void testNullStatus(){
        CreateActivityResponse lr = new CreateActivityResponse(null);
        assertEquals("", lr.getStatus());
    }
}