package db;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseCreateMockTest {

    @Mock
    private SQLiteDataSource dsMock;

    @Mock
    private Connection connMock;

    @Mock
    private Statement stmtMock;

    @Before
    public void init(){
        dsMock = mock(SQLiteDataSource.class);
        connMock = mock(Connection.class);
        stmtMock = mock(Statement.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockInitializeFailDbCreate(){
        DatabaseCreate db = new DatabaseCreate();
        DatabaseCreate dbSpy = Mockito.spy(db);
        Mockito.doReturn(false).when(dbSpy).createDatabase();
        assertFalse(dbSpy.initialize());
        Mockito.reset(dbSpy);
    }

    @Test
    public void testMockInitializeFailUserCreate(){
        DatabaseCreate db = new DatabaseCreate();
        DatabaseCreate dbSpy = Mockito.spy(db);
        Mockito.doReturn(true).when(dbSpy).createDatabase();
        Mockito.doReturn(false).when(dbSpy).createUsersTable();
        assertFalse(dbSpy.initialize());
        Mockito.reset(dbSpy);
    }

    @Test
    public void testMockInitializeFailActivitiesCreate(){
        DatabaseCreate db = new DatabaseCreate();
        DatabaseCreate dbSpy = Mockito.spy(db);
        Mockito.doReturn(true).when(dbSpy).createDatabase();
        Mockito.doReturn(true).when(dbSpy).createUsersTable();
        Mockito.doReturn(false).when(dbSpy).createActivitesTable();
        assertFalse(dbSpy.initialize());
        Mockito.reset(dbSpy);
    }

    @Test
    public void testMockInitializeFailFriendsCreate(){
        DatabaseCreate db = new DatabaseCreate();
        DatabaseCreate dbSpy = Mockito.spy(db);
        Mockito.doReturn(true).when(dbSpy).createDatabase();
        Mockito.doReturn(true).when(dbSpy).createUsersTable();
        Mockito.doReturn(true).when(dbSpy).createActivitesTable();
        Mockito.doReturn(false).when(dbSpy).createFriendsTable();
        assertFalse(dbSpy.initialize());
        Mockito.reset(dbSpy);
    }

    @Test
    public void testNullDatabseCreate() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(null);
        db.setDs(dsMock);
        assertFalse(db.createDatabase());

    }

    @Test
    public void testExceptionDatabseCreate() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertFalse(db.createDatabase());
    }

    @Test
    public void testExceptionConnect() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertNull(db.connect());
    }

    @Test
    public void testExceptionConnUsersTable() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertFalse(db.createUsersTable());
    }

    @Test
    public void testExceptionConnActivitiesTable() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertFalse(db.createActivitesTable());
    }

    @Test
    public void testExceptionConnFriendsTable() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertFalse(db.createFriendsTable());
    }

    @Test
    public void testExceptionUsersStatement() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenThrow(new SQLException());

        db.setDs(dsMock);
        assertFalse(db.createUsersTable());
    }

    @Test
    public void testExceptionActivitiesStatement() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenThrow(new SQLException());

        db.setDs(dsMock);
        assertFalse(db.createActivitesTable());
    }


    @Test
    public void testExceptionFriendsStatement() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenThrow(new SQLException());

        db.setDs(dsMock);
        assertFalse(db.createFriendsTable());
    }

    @Test
    public void testExceptionUsersExec() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertFalse(db.createUsersTable());
    }

    @Test
    public void testExceptionActivitiesExec() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertFalse(db.createActivitesTable());
    }

    @Test
    public void testExceptionFriendsExec() throws Exception {
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new SQLException());
        db.setDs(dsMock);
        assertFalse(db.createFriendsTable());
    }

    @Test
    public void testFinallyCreateUsersTableExcep() throws Exception{
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new SQLException());
        Mockito.doThrow(new SQLException()).when(stmtMock).close();
        db.setDs(dsMock);
        assertFalse(db.createUsersTable());
    }

    @Test
    public void testFinallyCreateFriendsTableExcep() throws Exception{
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new SQLException());
        Mockito.doThrow(new SQLException()).when(stmtMock).close();
        db.setDs(dsMock);
        assertFalse(db.createFriendsTable());
    }

    @Test
    public void testFinallyCreateActivitiesTableExcep() throws Exception{
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new SQLException());
        Mockito.doThrow(new SQLException()).when(stmtMock).close();
        db.setDs(dsMock);
        assertFalse(db.createActivitesTable());
    }

    @Test
    public void testFinallyCreateUsersTableExcepNull() throws Exception{
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new NullPointerException());
        Mockito.doThrow(new SQLException()).when(stmtMock).close();
        db.setDs(dsMock);
        assertFalse(db.createUsersTable());
    }

    @Test
    public void testFinallyCreateFriendsTableExcepNull() throws Exception{
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new NullPointerException());
        Mockito.doThrow(new SQLException()).when(stmtMock).close();
        db.setDs(dsMock);
        assertFalse(db.createFriendsTable());
    }

    @Test
    public void testFinallyCreateActivitiesTableExcepNull() throws Exception{
        DatabaseCreate db = new DatabaseCreate();
        when(dsMock.getConnection()).thenReturn(connMock);
        when(connMock.createStatement()).thenReturn(stmtMock);
        when(stmtMock.execute(anyString())).thenThrow(new NullPointerException());
        Mockito.doThrow(new SQLException()).when(stmtMock).close();
        db.setDs(dsMock);
        assertFalse(db.createActivitesTable());
    }


}