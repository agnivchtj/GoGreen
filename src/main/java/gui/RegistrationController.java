package gui;

import client.AuthenticationService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

/**
 * This class controls the interactivity on the registration screen.
 */
public class RegistrationController {

    /**
     * Setting the spacing.
     */
    private static final int SPACING = 17;

    /**
     * Setting the height.
     */
    private static final int HEIGHT = 50;

    /**
     * Setting the width.
     */
    private static final int WIDTH = 250;

    /**
     * @param textUsername text for the username.
     */
    @FXML
    private TextField textUsername;

    /**
     * @param textEmailID text for the email.
     */
    @FXML
    private TextField textEmailId;

    /**
     * @param textPassword text for the password.
     */
    @FXML
    private PasswordField textPassword;

    /**
     * @param registerPane pane for the register.
     */
    @FXML
    private AnchorPane registerPane;

    /**
     * Method to register the user.
     * @param event when the user clicks on the 'Register' button.
     * @throws Exception This is for any Exception.
     */
    public void registration(final ActionEvent event) throws Exception {
        String username = textUsername.getText();
        String emailId = textEmailId.getText();
        String password = textPassword.getText();
        if (AuthenticationService.register(username, emailId, password)) {
            URL url = new File("src/main/java/gui/"
                    + "RegistrationSuccess.fxml").toURI().toURL();
            AnchorPane successPane = FXMLLoader.load(url);
            registerPane.getChildren().setAll(successPane);
        } else {
            Stage primaryStage = new Stage();

            VBox layout = new VBox(SPACING);
            layout.setMinHeight(HEIGHT);
            layout.setMinWidth(WIDTH);
            Scene scene = new Scene(layout);
            primaryStage.setTitle("GoGreen");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);

            Label label = new Label("Registration error, please try again.");
            layout.getChildren().add(label);
            layout.setAlignment(Pos.CENTER);

            primaryStage.show();
        }
    }

    /**
     * Method to authenticate the user.
     * @param event when the user clicks on the 'here' button.
     * @throws Exception This is for any Exception.
     */
    public void login(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Login.fxml").toURI().toURL();
        AnchorPane loginPane = FXMLLoader.load(url);
        registerPane.getChildren().setAll(loginPane);
    }
}
