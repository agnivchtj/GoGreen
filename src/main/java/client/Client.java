package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Client class that communicates with the server.
 */
public final class Client {
    /**
     * Base url for the api.
     */
    private static final String URL = "http://localhost:8080/";

    /**
     * Connection timeout for the HTTP request.
     */
    private static final int CONNECTION_TIMEOUT = 5000;

    /**
     * Private constructor.
     */
    private Client() { }

    /**
     * Method to send a JSON request to a specified url.
     * @param apUrl of the API server.
     * @param body containing a stringfied version of the JSON request.
     * @return a String containing the JSON responses.
     */
    public static String sendReq(final String apUrl, final String body) {
        try {
            //Craft API Url
            URL apiUrl = new URL(apUrl);

            //Start connection with server
            HttpURLConnection conn = (HttpURLConnection)
                    apiUrl.openConnection();
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestProperty("Content-Type",
                    "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");

            //Send JSON body
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes("UTF-8"));
            os.close();

            //Read the responses
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();

            //Return string containing JSON format
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Get base url of the api.
     * @return the base url of the api.
     */
    public static String getUrl() {
        return URL;
    }
}
