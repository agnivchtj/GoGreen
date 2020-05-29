package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This class handles the login page of the client.
 */
public class DisclaimerController {

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
     * Method to login the user.
     * @param event when user clicks on the Login button.
     * @throws Exception this is for an IO Exception.
     */

    @FXML
    private AnchorPane disclaimerPane;

    /**
     * The button will take the user to the website.
     * @throws URISyntaxException - throws URI Syntax Exception
     * @throws IOException - throws an IO Exception
     */
    @FXML
    private void goWebsite() throws URISyntaxException, IOException {
        if (Desktop.isDesktopSupported() && Desktop
                .getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(new URI("http://www.yousustain.com"));
        }
    }

    /**
     * Function to return to the previous tab.
     * @param event - event used to go back to Track C02
     * @throws Exception - throws exception
     */
    public void returnToTrackC02(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/features/trackco2/TrackC02.fxml")
                .toURI().toURL();
        AnchorPane trackC02Pane = FXMLLoader.load(url);
        disclaimerPane.getChildren().setAll(trackC02Pane);
    }
}
