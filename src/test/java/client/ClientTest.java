package client;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void testException(){
        assertNull(Client.sendReq("NotAUrl", "NotJSON"));
    }

}