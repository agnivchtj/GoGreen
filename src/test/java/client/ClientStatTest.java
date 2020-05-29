package client;

import client.messages.ClientStat;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientStatTest {

    @Test
    public void testNullConstructor(){
        ClientStat s = new ClientStat(null, 0, 0.0);
        assertEquals("", s.getUsername());
    }

    @Test
    public void testGetUsername() {
        ClientStat s = new ClientStat("test", 0, 0.0);
        assertEquals("test", s.getUsername());
    }

    @Test
    public void testGetPoints(){
        ClientStat s = new ClientStat("test", 10, 1.2);
        assertEquals(10, s.getPoints());
    }

    @Test
    public void testGetSaved(){
        ClientStat s = new ClientStat("test", 10, 1.2);
        assertEquals(1.2, s.getSaved(), 0.1);
    }
}