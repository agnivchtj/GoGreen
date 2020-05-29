package gui.members;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;


/**
 * The class that controls the Arash fxml page.
 */
public class ArashController {

    /**
     * The class that controls the members fxml page.
     */

    /**
     * Setting the spacing of the VBox.
     */
    private static final int SPACING = 17;

    /**
     * Setting the width of the popup window.
     */
    private static final int LAYOUT_MIN_WIDTH = 250;

    /**
     * Setting the height of the popup window.
     */
    private static final int LAYOUT_MIN_HEIGHT = 50;

    /**
     * Setting the height of the other popup window.
     */
    private static final int OTHER_HEIGHT = 100;

    /**
     * @param arashPane the Arash screen.
     */
    @FXML
    private AnchorPane arashPane;

    /**
     * Function to return to the Agniv Pane.
     * @param event go to members when clicked
     * @throws Exception - throws an exception if error occurs
     */
    public void members(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/members.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        arashPane.getChildren().setAll(membersController);
    }

    /**
     * Method to go back to the main tab.
     *
     * @param event when the user clicks 'Return to main menu' button.
     * @throws Exception This is for any Exception.
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        arashPane.getChildren().setAll(mainPane);
    }
}

