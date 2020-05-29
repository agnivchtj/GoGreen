package gui.members;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;


/**
 * The class that controls the Pim fxml page.
 */
public class PimController {

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
     * @param pimPane the Pim's screen.
     */
    @FXML
    private AnchorPane pimPane;

    /**
     *
     * @param event - Return to the members pane.
     * @throws Exception - throw exception if exception found
     */
    public void members(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/members.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        pimPane.getChildren().setAll(membersController);
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
        pimPane.getChildren().setAll(mainPane);
    }
}

