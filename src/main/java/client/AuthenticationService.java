package client;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

/**
 * This class is used to authenticate the user during login and registration.
 */
public final class AuthenticationService {
    /**
     * Creates a rest template.
     */
    private static RestTemplate restTemplate = new RestTemplate();

    /**
     * Private constructor.
     */
    private AuthenticationService() { }

    /**
     * Method to send a request to register a new user.
     * @param username the username filled.
     * @param email the email ID filled.
     * @param password the password filled.
     * @return the response1 status.
     */
    public static boolean register(
            final String username, final String email, final String password) {
        // Create the registration request which is converted to
        // JSON so it can be sent as the POST body
        RegistrationRequest registrationRequest = new RegistrationRequest(
                username, email, new BCryptPasswordEncoder().encode(password));

        // You're calling the post for entity, making a POST request
        // 1st - url, body, how you want it to be parsed

        ResponseEntity<RegistrationResponse> response1 =
                restTemplate.postForEntity(Client.getUrl() + "register",
                registrationRequest,
                RegistrationResponse.class);

        return response1.getBody().getStatus().equals("Success");
    }

    /**
     * Method to send a login request to the server.
     * @param username the username made by the user.
     * @param password the password created by the user.
     * @return the response2 status.
     */
    public static boolean signIn(final String username, final String password) {
        String method = "username_only";

        LoginRequest loginRequest = new LoginRequest(
                method, username, password);

        ResponseEntity<LoginResponse> response2 = restTemplate.postForEntity(
                Client.getUrl() + "auth",
                loginRequest,
                LoginResponse.class);

        return response2.getBody().getStatus().equals("Success");
    }
}
