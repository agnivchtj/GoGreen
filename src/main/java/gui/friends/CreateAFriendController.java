package gui.friends;

import client.ActivitiesApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * The class that controls the Create A Friend fxml page.
 */
public class CreateAFriendController {

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
     * This is the anchor pane for this class.
     */
    @FXML
    private AnchorPane createFriendActivity;

    /**
     * This is the text field.
     */
    @FXML
    private TextField textFriend;

    /**
     * Method to add a friend.
     * @param event when the user clicks on the 'Add!' button.
     * @throws Exception This is for any Exception.
     */
    public void addFriend(final ActionEvent event) throws Exception {
        Scanner sc = new Scanner(new File("files/config/user.txt"));
        String username = sc.nextLine().trim();
        String friend = textFriend.getText();

        if (ActivitiesApiService.createFriend(username, friend)) {
            Stage stage = new Stage();

            VBox friendLayout = new VBox(SPACING);
            friendLayout.setMinWidth(LAYOUT_MIN_WIDTH);
            friendLayout.setMinHeight(LAYOUT_MIN_HEIGHT);
            Scene scene = new Scene(friendLayout);
            stage.setScene(scene);

            Label newLabel = new Label("Friend added successfully!");
            friendLayout.setAlignment(Pos.CENTER);
            friendLayout.getChildren().add(newLabel);

            stage.setTitle("GoGreen");
            stage.setResizable(false);
            stage.show();

        } else {
            Stage stage = new Stage();
            stage.setTitle("GoGreen");
            stage.setResizable(false);

            VBox layout = new VBox(SPACING);
            Label label = new Label(
                    "Friend could not be added. Please try again.");
            layout.getChildren().add(label);
            layout.setAlignment(Pos.CENTER);
            layout.setMinHeight(OTHER_HEIGHT);
            layout.setMinWidth(LAYOUT_MIN_WIDTH);

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Method to go back to the friends tab.
     * @param event when the user clicks the 'Back' button.
     * @throws Exception This is for any Exception.
     */
    public void backToFriends(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/friends/Friends.fxml").toURI().toURL();
        AnchorPane friendPane = FXMLLoader.load(url);
        createFriendActivity.getChildren().setAll(friendPane);
    }

    /**
     * Method to go back to the main tab.
     * @param event when the user clicks 'Return to main menu' button.
     * @throws Exception This is for any Exception.
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        createFriendActivity.getChildren().setAll(mainPane);
    }
}

