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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

/**
 * This class handles the login page of the client.
 */
public class LoginController {

    /**
     * Spacing of the VBox.
     * @param the spacing of the VBox.
     */
    private static final int SPACING = 17;

    /**
     * Setting the min height.
     */
    private static final int MIN_HEIGHT = 50;

    /**
     * Setting the min width.
     */
    private static final int MIN_WIDTH = 250;

    /**
     * @param textUsername.
     */
    @FXML
    private TextField textUsername;

    /**
     * @param textPassword.
     */
    @FXML
    private PasswordField textPassword;

    /**
     * @param loginPane.
     */
    @FXML
    private AnchorPane loginPane;

    /**
     * Method to login the user.
     * @param event when user clicks on the Login button.
     * @throws Exception this is for an IO Exception.
     */
    public void login(final ActionEvent event) throws Exception {
        String username = textUsername.getText();
        String password = textPassword.getText();
        if (AuthenticationService.signIn(username, password)) {
            URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
            AnchorPane mainPane = FXMLLoader.load(url);
            loginPane.getChildren().setAll(mainPane);
            try {
                BufferedWriter wr = new BufferedWriter(
                        new FileWriter("files/config/user.txt"));
                wr.write(username);
                wr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Stage stage = new Stage();
            stage.setTitle("GoGreen");
            stage.setResizable(false);

            VBox layout = new VBox(SPACING);
            layout.setMinHeight(MIN_HEIGHT);
            layout.setMinWidth(MIN_WIDTH);
            Scene primaryScene = new Scene(layout);
            stage.setScene(primaryScene);

            Label label = new Label("Login error, please try again.");
            layout.getChildren().add(label);
            layout.setAlignment(Pos.CENTER);

            stage.show();
        }
    }

    /**
     * Method to register the user.
     * @param event when user clicks on the 'here' link.
     * @throws Exception this is for any Exception.
     */
    public void register(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/"
                + "Registration.fxml").toURI().toURL();
        AnchorPane registerPane = FXMLLoader.load(url);
        loginPane.getChildren().setAll(registerPane);
    }
}
