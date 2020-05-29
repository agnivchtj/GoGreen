package db;

import api.Activity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseQueryMockTest {

    @Mock
    private PreparedStatement pstMock;

    @Mock
    private Connection connMock;

    @Mock
    private ResultSet rsMock;

    @Before
    public void init(){
        pstMock = mock(PreparedStatement.class);
        connMock = mock(Connection.class);
        rsMock = mock(ResultSet.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockQueryTable()  {
        try{
            DatabaseCreate dbs = new DatabaseCreate("main.db");
            dbs.initialize();
            DatabaseQuery db = new DatabaseQuery(dbs.connect());
            when(pstMock.executeQuery()).thenThrow(new SQLException());
            assertFalse(db.authUser("", "", ""));
            assertFalse(db.addNewUserActivity("", "", "", null));
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testAuthWrongUser() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(false);
        assertFalse(dbquery.authUser("username_only", "tes", "tes"));
    }

    @Test
    public void testExceptionPrepIdFromUser() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        assertNull(dbquery.getIdFromUsername("tes"));
    }

    @Test
    public void testExceptionPrepDelDup() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        dbquery.deleteActivityDuplicates();
    }

    @Test
    public void testExceptionExecDelDup() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        when(pstMock.execute()).thenThrow(new SQLException());
        dbquery.deleteActivityDuplicates();
    }

    @Test
    public void testExceptionExcepAddFriend() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        assertFalse(dbquery.addFriend("tes", "tom"));
    }

    @Test
    public void testExcepAuthUserSetString() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        assertFalse(dbquery.authUser("tes", "tes", "tes"));

    }

    @Test
    public void testExcepAuthUserExecQuery() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenThrow(new SQLException());
        assertFalse(dbquery.authUser("tes", "tes", "tes"));
    }

    @Test
    public void testExcepAuthUserNext() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenThrow(new SQLException());
        assertFalse(dbquery.authUser("tes", "tes", "tes"));

    }

    @Test
    public void testExcepAutGetString() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);
        Mockito.doThrow(new SQLException()).when(rsMock).getString(anyString());
        assertFalse(dbquery.authUser("tes", "tes", "tes"));
    }

    @Test
    public void testExcepIdFromUserSetString() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        assertNull(dbquery. getIdFromUsername("tes"));
    }

    @Test
    public void testExcepIdFromUserExecQuery() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenThrow(new SQLException());
        assertNull(dbquery. getIdFromUsername("tes"));
    }

    @Test
    public void testExcepIdFromUserRsNext() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenThrow(new SQLException());
        assertNull(dbquery. getIdFromUsername("tes"));
    }

    @Test
    public void testExcepIdFromUserGetString() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(false);
        when(rsMock.getString(anyString())).thenThrow(new SQLException());
        assertNull(dbquery. getIdFromUsername("tes"));
    }

    @Test
    public void testAddNewUserNonExistent() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.execute()).thenReturn(true);
        when(pstMock.executeQuery()).thenThrow(new SQLException());
        assertEquals(-1, dbquery.addNewUser("tes", "tes", "tes"));
    }

    @Test
    public void testAddNewUserThrowPrep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        assertEquals(-1, dbquery.addNewUser("", "", ""));
    }

    @Test
    public void testAddNewUserThrowFirstSetString() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(eq(1), anyString());
        assertEquals(-1, dbquery.addNewUser("", "", ""));
    }

    @Test
    public void testAddNewUserThrowSecondSetString() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(eq(2), anyString());
        assertEquals(-1, dbquery.addNewUser("", "", ""));
    }

    @Test
    public void testAddNewUserThrowThirdSetString() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(eq(3), anyString());
        assertEquals(-1, dbquery.addNewUser("", "", ""));
    }

    @Test
    public void testAddNewUserThrowExec() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.execute()).thenThrow(new SQLException());
        assertEquals(-1, dbquery.addNewUser("", "", ""));
    }

    @Test
    public void testGetStatPrepExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        assertNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetStatSetStringExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        assertNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetStatExecExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenThrow(new SQLException());
        assertNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetStatFalseResExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(false);
        assertNotNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetStatResExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenThrow(new SQLException());
        assertNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetStatGetIntExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getInt(anyString())).thenThrow(new SQLException());
        assertNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetFriendsPrepExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        assertNull(dbquery.getUserFriends(""));
    }

    @Test
    public void testGetFriendsSetStringExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        assertNull(dbquery.getUserFriends(""));
    }

    @Test
    public void testGetFriendsExecQueryExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenThrow(new SQLException());
        assertNull(dbquery.getUserFriends(""));
    }

    @Test
    public void testGetFriendsRsNextExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenThrow(new SQLException());
        assertNull(dbquery.getUserFriends(""));
    }

    @Test
    public void testGetFriendsGetStringExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getString(anyString())).thenThrow(new SQLException());
        assertNull(dbquery.getUserFriends(""));
    }

    @Test
    public void testGetUserActivitiesPrepExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        assertNull(dbquery.getUserActivities(""));
    }

    @Test
    public void testGetUserActivitiesSetStringExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        assertNull(dbquery.getUserActivities(""));
    }

    @Test
    public void testGetUserActivitiesExecQueryExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenThrow(new SQLException());
        assertNull(dbquery.getUserActivities(""));
    }

    @Test
    public void testGetUserActivitiesRsNextExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenThrow(new SQLException());
        assertNull(dbquery.getUserActivities(""));
    }

    @Test
    public void testGetUserActivitiesGetStringExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeQuery()).thenReturn(rsMock);
        when(rsMock.next()).thenReturn(true);
        when(rsMock.getString(anyString())).thenThrow(new SQLException());
        assertNull(dbquery.getUserActivities(""));
    }

    @Test
    public void testAddActivityGetTrueConnExcep() throws Exception {
        Map<String, Integer> s = mock(Map.class);
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(s.get(anyString())).thenReturn(1);
        when(connMock.prepareStatement(anyString())).thenThrow(new SQLException());
        assertFalse(dbquery.addNewUserActivity("", "", "", s));
    }

    @Test
    public void testAddActivityFirstSetExcep() throws Exception {
        Map<String, Integer> s = mock(Map.class);
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(s.get(anyString())).thenReturn(1);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(eq(1), anyString());
        assertFalse(dbquery.addNewUserActivity("", "", "", s));
    }

    @Test
    public void testAddActivitySecondSetExcep() throws Exception {
        Map<String, Integer> s = mock(Map.class);
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(s.get(anyString())).thenReturn(1);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(eq(2), anyString());
        assertFalse(dbquery.addNewUserActivity("", "", "", s));
    }

    @Test
    public void testAddActivityThirdSetExcep() throws Exception {
        Map<String, Integer> s = mock(Map.class);
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(s.get(anyString())).thenReturn(1);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(eq(3), anyString());
        assertFalse(dbquery.addNewUserActivity("", "", "", s));
    }

    @Test
    public void testAddActivityFourthSetExcep() throws Exception {
        Map<String, Integer> s = mock(Map.class);
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(s.get(anyString())).thenReturn(1);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setInt(eq(4), anyInt());
        assertFalse(dbquery.addNewUserActivity("", "", "", s));
    }

    @Test
    public void testAddActivityExecUpExcep() throws Exception {
        Map<String, Integer> s = mock(Map.class);
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(s.get(anyString())).thenReturn(1);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doNothing().when(pstMock).setString(anyInt(), anyString());
        when(pstMock.executeUpdate()).thenThrow(new SQLException());
        assertFalse(dbquery.addNewUserActivity("", "", "", s));
    }

    @Test
    public void testGetStatFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetStatFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertNull(dbquery.getStat("", ""));
    }

    @Test
    public void testGetUserFriendsFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertNull(dbquery.getUserFriends(""));
    }

    @Test
    public void testGetUserFriendsFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertNull(dbquery.getUserFriends(""));
    }

    @Test
    public void testGetUserActivitiesFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertNull(dbquery.getUserActivities(""));
    }

    @Test
    public void testGetUserActivitiesFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertNull(dbquery.getUserActivities(""));
    }

    @Test
    public void testAddFriendFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertFalse(dbquery.addFriend("", ""));
    }

    @Test
    public void testAddFriendFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertFalse(dbquery.addFriend("", ""));
    }

    @Test
    public void testAddNewUserActivityFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertFalse(dbquery.addNewUserActivity("", "", "", new HashMap<>()));
    }

    @Test
    public void testAddNewUserActivityFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertFalse(dbquery.addNewUserActivity("", "", "", new HashMap<>()));
    }

    @Test
    public void testGetIdUserFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertNull(dbquery.getIdFromUsername(""));
    }

    @Test
    public void testGetIdUserFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertNull(dbquery.getIdFromUsername(""));
    }

    @Test
    public void testAuthUserFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertFalse(dbquery.authUser("", "", ""));
    }

    @Test
    public void testAuthUserFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertFalse(dbquery.authUser("", "", ""));
    }

    @Test
    public void testAddUserFinallyExcep() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new SQLException()).when(pstMock).close();
        assertEquals(-1, dbquery.addNewUser("", "", ""));
    }

    @Test
    public void testAddUserFinallyExcepNull() throws Exception {
        DatabaseQuery dbquery = new DatabaseQuery(connMock);
        when(connMock.prepareStatement(anyString())).thenReturn(pstMock);
        Mockito.doThrow(new SQLException()).when(pstMock).setString(anyInt(), anyString());
        Mockito.doThrow(new NullPointerException()).when(pstMock).close();
        assertEquals(-1, dbquery.addNewUser("", "", ""));
    }

}