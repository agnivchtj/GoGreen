package api;

import db.DatabaseCreate;
import db.DatabaseQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Main class to start the API server.
 */
@RestController
@SpringBootApplication
public class ApiServer {

    /**
     * Attribute to reference the SQLite database.
     */
    private static DatabaseCreate db;

    /**
     * Spring boot application that can be shutted down.
     */
    private static ConfigurableApplicationContext ctx;

    /**
     * Value which can be used to decrease
     * the length of the method array.
     */
    private static final int DECREASE = 5;

    /**
    Mapping for registration see API Docs for more details.
     @param payload contains the body of the request which is a string
     version of its JSON representation.
     @return Response object based on the requested body.
     */
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST)
    public Response createNewUser(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String username = (String) payload.get("username");
        String password = (String) payload.get("password");
        String email = (String) payload.get("email");


        //Check if anything is missing
        if (username == null) {
            return new Error("Username is not valid");
        }
        if (password == null) {
            return new Error("Password is not valid");
        }
        if (email == null) {
            return new Error("Email is not valid");
        }

        // Create database user and get id
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        int id = dbquery.addNewUser(username, email, password);
        if (id > 0) {
            return new User("Added", id);
        } else {
            return new Error("Can't insert into database");
        }

    }

    /**
     Mapping for registration see API Docs for more details.
     @param payload contains the body of the request which is a string.
     version of its JSON representation.
     @return Response object based on the requested body.
     */
    @RequestMapping(
            value = "/activity",
            method = RequestMethod.POST)
    public Response createNewActivity(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String username = (String) payload.get("username");
        String type = (String) payload.get("type");
        String comment = (String) payload.get("comment");

        //Check if anything is missing
        if (username == null) {
            return new Error("Username is not valid");
        }
        if (type == null) {
            return new Error("Type is not valid");
        }
        if (comment == null) {
            return new Error("Comment is not valid");
        }

        // Create database user and get id
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        if (dbquery.addNewUserActivity(
                dbquery.getIdFromUsername(username),
                type, comment, db.getSaved())) {
            return new Response("Added");
        } else {
            return new Error("Can't insert into database");
        }

    }

    /**
     * Mapping for getting user activities see API Docs for more details.
     * @param payload contains the body of the request which is a string
     * @return Response object based on the requested body.
     */
    @RequestMapping(
            value = "/getactivity",
            method = RequestMethod.POST)
    public Response getActivities(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String username = (String) payload.get("username");

        //Check if anything is missing
        if (username == null) {
            return new Error("Username is not valid");
        }

        // Create database user and get id
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        List<Activity> res = dbquery.getUserActivities(
                dbquery.getIdFromUsername(username));
        if (res != null) {
            return new Activities(res);
        } else {
            return new Error("Username is not valid");
        }

    }



    /**
     Mapping for registration see API Docs for more details.
     @param payload contains the body of the request which is a string
     version of its JSON representation.
     @return Response object based on the requested body.
     */
    @RequestMapping(
            value = "/friend",
            method = RequestMethod.POST)
    public Response createNewFriend(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String username = (String) payload.get("username");
        String friend = (String) payload.get("friend");

        //Check if anything is missing
        if (username == null) {
            return new Error("Username is not valid");
        }
        if (friend == null) {
            return new Error("Friend is not valid");
        }

        // Create database user and get id
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        boolean status = dbquery.addFriend(dbquery.getIdFromUsername(username),
                dbquery.getIdFromUsername(friend));
        if (status) {
            return new Response("Added");
        } else {
            return new Error("Can't insert into database");
        }

    }

    /**
     * Mapping for getting user friends see API Docs for more details.
     * @param payload contains the body of the request which is a string
     * @return Response object based on the requested body.
     */
    @RequestMapping(
            value = "/getfriends",
            method = RequestMethod.POST)
    public Response getFriends(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String username = (String) payload.get("username");

        //Check if anything is missing
        if (username == null) {
            return new Error("Username is not valid");
        }

        // Create database user and get id
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        List<Friend> res = dbquery.getUserFriends(
                dbquery.getIdFromUsername(username));
        if (res != null) {
            return new Friends(res);
        } else {
            return new Error("Username is not valid");
        }

    }

    /**
     * Endpoints for the stats of a single user see API docs.
     * @param payload contains the username.
     * @return all the stastistics present for that user.
     */
    @RequestMapping(
            value = "/stat",
            method = RequestMethod.POST)
    public Response getStat(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String username = (String) payload.get("username");

        //Check if anything is missing
        if (username == null) {
            return new Error("Username is not valid");
        }

        // Create database user and get id
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        Stat res = dbquery.getStat(
                username, dbquery.getIdFromUsername(username));
        if (res != null) {
            return res;
        } else {
            return new Error("Username is not valid");
        }

    }

    /**
     * Endpoint for statistics for a certain user and all his friends.
     * @param payload contains the username.
     * @return an array containing all the statistics.
     */
    @RequestMapping(
            value = "/stats",
            method = RequestMethod.POST)
    public Response getStats(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String username = (String) payload.get("username");

        //Check if anything is missing
        if (username == null) {
            return new Error("Username is not valid");
        }

        // Create database user and get id
        DatabaseQuery dbquery = new DatabaseQuery(db.connect());
        Stats res = dbquery.getStats(
                username, dbquery.getIdFromUsername(username));
        if (res != null) {
            return res;
        } else {
            return new Error("Username is not valid");
        }

    }

    /**
    Mapping for authorization see API Docs for more details.
     @param payload contains the body of the request which is a string
     version of its JSON representation.
     @return Response object based on the requested body.
     */
    @RequestMapping(
            value = "/auth",
            method = RequestMethod.POST)
    public Response authUser(
            final @RequestBody Map<String, Object> payload) {
        //Get request parameters
        String method = (String) payload.get("method");
        String password = (String) payload.get("password");
        String identity = (String) payload.get("identity");

        //Check if anything is missing
        if (method == null) {
            return new Error("Method is not valid");
        }
        if (password == null) {
            return new Error("Password is not valid");
        }
        if (identity == null) {
            return new Error("Identity is not valid");
        }

        //
        String[] methods = {"id_only", "username_only", "email_only"};
        if (method.equals("id_only")
                || method.equals("username_only")
                || method.equals("email_only")) {
            DatabaseQuery dbquery = new DatabaseQuery(db.connect());
            boolean status = dbquery.authUser(method.substring(0,
                    method.length() - DECREASE),
                    identity, password);
            if (status) {
                return new Response("Success");
            } else {
                return new Error("Invalid Credentials");
            }
        } else {
            return new Error("Method is not valid");
        }
    }

    /**
     * Default mapping if the endpoints requested is not mapped.
     * @return Error status.
     */
    @RequestMapping("/*")
    public Response defaultReq() {
        return (Response) new Error("Specified path is not correct");
    }

    /**
     * Main class to start the server.
     * @param args no arguments needed.
     */
    public static void main(final String[] args) {
        db = new DatabaseCreate("main.db");
        if (db.initialize()) {
            ctx = SpringApplication.run(ApiServer.class, args);
        } else {
            System.out.println("Error initializing database");
            stop();
        }
    }

    /**
     * Stop the api server remotely.
     */
    public static void stop() {
        ctx.close();
    }

}
