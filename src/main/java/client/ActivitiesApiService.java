package client;

import api.Activity;
import api.Friend;
import api.Stat;
import client.messages.ClientStat;
import client.messages.requests.CreateActivityRequest;
import client.messages.requests.CreateFriendRequest;
import client.messages.responses.CreateActivityResponse;
import client.messages.responses.CreateFriendResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that handles the client's interaction with activities.
 */
public final class ActivitiesApiService {
    /**
     * This class uses a rest template.
     */
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * Private constructor.
     */
    private ActivitiesApiService() { }

    /**
     * Method to create send a request to create an activity.
     * @param username the username.
     * @param type the type of meal.
     * @param comment the comments.
     * @return whether it was added or not.
     */
    public static boolean createActivity(
            final String username, final String type, final String comment) {
        CreateActivityRequest createActivityRequest = new CreateActivityRequest(
                username, type, comment);

        ResponseEntity<CreateActivityResponse> requestactivity =
                restTemplate.postForEntity(Client.getUrl() + "activity",
                        createActivityRequest,
                        CreateActivityResponse.class);

        return requestactivity.getBody().getStatus().equals("Added");
    }

    /**
     * Method to send a request in order to create a friend.
     * @param username the username of the user.
     * @param friend the username of the friend.
     * @return the status of the friend request.
     */
    public static boolean createFriend(
            final String username, final String friend) {
        CreateFriendRequest createFriendRequest = new CreateFriendRequest(
                username, friend);

        ResponseEntity<CreateFriendResponse> requestfriend =
                restTemplate.postForEntity(Client.getUrl() + "friend",
                        createFriendRequest,
                        CreateFriendResponse.class);

        return requestfriend.getBody().getStatus().equals("Added");
    }

    /**
     * Method to get the list activities done by a user.
     * @param username the username.
     * @return the activity requested.
     */
    public static List<Activity> getActivity(final String username) {
        if (username == null) {
            return null;
        }
        JSONObject req = new JSONObject();

        try {
            req.put("username", username);
            String resp = Client.sendReq(
                    Client.getUrl() + "getactivity", req.toString());

            JSONObject resJ = new JSONObject(resp);
            if (resJ.get("status").equals("Success")) {
                JSONArray jsonarray = resJ.getJSONArray("activities");
                List<Activity> res = new ArrayList<>();
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String type = jsonobject.getString("type");
                    String comment = jsonobject.getString("comment");
                    String timestamp = jsonobject.getString("timestamp");
                    String points = jsonobject.getString("points");

                    Activity toAdd = new Activity(type, comment,
                            timestamp, points);
                    res.add(toAdd);
                }
                return res;
            } else {
                return null;
            }
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * Method to get the list of friends of a certain user.
     * @param username the username.
     * @return the friend(s) requested.
     */
    public static List<Friend> getFriend(final String username) {
        if (username == null) {
            return null;
        }
        JSONObject ret = new JSONObject();

        try {
            ret.put("username", username);
            String str = Client.sendReq(
                    Client.getUrl() + "getfriends", ret.toString());

            JSONObject resJ = new JSONObject(str);
            if (resJ.get("status").equals("Success")) {
                JSONArray jsonarray = resJ.getJSONArray("friends");
                List<Friend> result = new ArrayList<>();
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String friend = jsonobject.getString("username");

                    Friend toAdd = new Friend(friend);
                    result.add(toAdd);
                }
                return result;
            } else {
                return null;
            }
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * Method to get the statistics of a user.
     * @param username the client's username.
     * @return the requested statistics.
     */
    public static Stat getStat(final String username) {
        if (username == null) {
            return null;
        }
        JSONObject ret = new JSONObject();

        try {
            ret.put("username", username);
            String string = Client.sendReq(
                    Client.getUrl() + "stat", ret.toString());

            JSONObject resJ = new JSONObject(string);
            if (resJ.get("status").equals("Success")) {
                int score = resJ.getInt("points");
                double saved = resJ.getDouble("saved");
                JSONArray jsonarray = resJ.getJSONArray("recent");

                List<Activity> recent = new ArrayList<>();
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);
                    String type = jsonobject.getString("type");
                    String comment = jsonobject.getString("comment");
                    String points = jsonobject.getString("points");
                    String timestamp = jsonobject.getString("timestamp");

                    Activity toAdd = new Activity(type, comment,
                            timestamp, points);
                    recent.add(toAdd);
                }
                Stat stat = new Stat(username, score, saved, recent);
                return stat;
            } else {
                return null;
            }
        } catch (JSONException e) {
            return null;
        }
    }

    /**
     * Method to get statistics of a user and his friends.
     * @param username either your or your friend's username.
     * @return your or your friend's statistics.
     *
     */
    public static List<ClientStat> getStats(final String username) {
        if (username == null) {
            return null;
        }
        JSONObject ret = new JSONObject();

        try {
            ret.put("username", username);
            String string = Client.sendReq(
                    Client.getUrl() + "stats", ret.toString());

            JSONObject resJ = new JSONObject(string);
            if (resJ.get("status").equals("Success")) {
                JSONArray array = resJ.getJSONArray("stats");
                List<ClientStat> allStats = new ArrayList<>();

                for (int j = 0; j < array.length(); j++) {
                    JSONObject jsonobj = array.getJSONObject(j);
                    allStats.add(new ClientStat(
                            jsonobj.getString("username"),
                            jsonobj.getInt("points"),
                            jsonobj.getDouble("saved")));
                }
                return allStats;
            } else {
                return null;
            }
        } catch (JSONException e) {
            return null;
        }
    }
}
