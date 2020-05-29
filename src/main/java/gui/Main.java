package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

/**
 * This class contains the method which starts the application.
 */
public class Main extends Application {
    /**
     * @param loginScreen_width width of the login screen.
     */
    private final int loginScrWidth = 600;

    /**
     * @param loginScr_height height of the login screen.
     */
    private final int loginScrHeight = 400;

    /**
     * @param loginWindow stage for login window.
     */
    private Stage loginWindow;

    /**
     * Method to start the application.
     * @param primaryStage this is the window in javaFX.
     * @throws Exception this is for any Exception.
     */
    public void start(final Stage primaryStage) throws Exception {
        loginWindow = primaryStage;

        URL url = new File("src/main/java/gui/Login.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene loginScreen = new Scene(root, loginScrWidth, loginScrHeight);
        loginWindow.setTitle("GoGreen");
        loginWindow.setScene(loginScreen);
        loginWindow.setResizable(false);
        loginWindow.show();
    }

    /**
     * Main method of class that starts the gui application.
     * @param args no arguments needed.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}

