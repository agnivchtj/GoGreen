package gui.members;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;

/**
 * The class that controls the members fxml page.
 */

public class MembersController {
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
     * @param membersPane the members screen.
     */
    @FXML
    private AnchorPane membersPane;
    /**
     * Method to go back to the main tab.
     *
     * @param event when the user clicks 'Return to main menu' button.
     * @throws Exception This is for any Exception.
     */

    public void agniv(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/AgnivMember.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        membersPane.getChildren().setAll(membersController);
    }

    /**
     * Function to return to the ArashMember Pane.
     *
     * @param event when clicked opens the arash pane
     * @throws Exception - throws an error if something goes wrong
     */
    public void arash(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/ArashMember.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        membersPane.getChildren().setAll(membersController);
    }

    /**
     * Function to return to the KurtMember Pane.
     *
     * @param event when clicked opens the kurt pane
     * @throws Exception - throws an error if something goes wrong
     */
    public void kurt(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/KurtMember.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        membersPane.getChildren().setAll(membersController);
    }

    /**
     * Function to return to the PimMember Pane.
     *
     * @param event when clicked opens the pim pane
     * @throws Exception - throws an error if something goes wrong
     */
    public void pim(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/PimMember.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        membersPane.getChildren().setAll(membersController);
    }

    /**
     * Function to goes to the members pane.
     *
     * @param event when clicked opens the members pane
     * @throws Exception - throws an error if something goes wrong
     */
    public void members(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/members.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        membersPane.getChildren().setAll(membersController);
    }

    /**
     * Function to return to the main pane.
     *
     * @param event when clicked goes back to the main pane
     * @throws Exception - throws an error if something goes wrong
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        membersPane.getChildren().setAll(mainPane);
    }
}
