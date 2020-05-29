package db;

import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseCreateTest {

    @Test
    public void testEmptyConstructor(){
        DatabaseCreate db = new DatabaseCreate();
        assertEquals("jdbc:sqlite:files\\db\\main.db", db.getUrl());
    }

    @Test
    public void testCustomCorrectConstructor(){
        DatabaseCreate db = new DatabaseCreate("correct.db");
        assertEquals("jdbc:sqlite:files\\db\\correct.db", db.getUrl());
    }

    @Test
    public void testInvalidConstructor(){
        DatabaseCreate db = new DatabaseCreate("Notavalidname");
        assertEquals("jdbc:sqlite:files\\db\\main.db", db.getUrl());
    }

    @Test
    public void testGetConnection(){
        DatabaseCreate db = new DatabaseCreate();
        assertNotNull(db.connect());
    }

    @Test
    public void testInitialize(){
        DatabaseCreate db = new DatabaseCreate();
        assertTrue(db.initialize());
    }

    @Test
    public void testSaved() {
        DatabaseCreate db = new DatabaseCreate();
        assertNotNull(db.getSaved());
    }

    @Test
    public void testNullSaved(){
        DatabaseCreate db = new DatabaseCreate();
        db.setActivities(null);
        assertNotNull(db.getSaved());
    }

}