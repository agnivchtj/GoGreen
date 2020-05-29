package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;

/**
 * This class controls the interactivity on the successful Registration page.
 */
public class RegistrationSuccessController {
    /**
     * @param labelStatus label for the status.
     */
    @FXML
    private Label labelStatus;

    /**
     * @param backToLogin button to go back to login.
     */
    @FXML
    private Button backToLogin;

    /**
     * @param successPane pane for success register.
     */
    @FXML
    private AnchorPane successPane;

    /**
     * Method to go back to the login page.
     * @param event when the user clicks on the 'Go back to Login' button.
     * @throws Exception This is for any Exception.
     */
    public void backToLogin(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Login.fxml").toURI().toURL();
        AnchorPane loginPane = FXMLLoader.load(url);
        successPane.getChildren().setAll(loginPane);
    }
}
