package gui.features.badges;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.net.URL;

/**
 * The class that controls the contents of the badges features page.
 */
public class BadgesController {
    /**
     * Setting the spacing of the VBox.
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
     * @param badgespane.
     */
    @FXML
    private AnchorPane badgesPane;

    /**
     * return to the main menu.
     * @param event when the user clicks on the 'back' button
     * @throws Exception This is for any Exception
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        badgesPane.getChildren().setAll(mainPane);
    }
}
