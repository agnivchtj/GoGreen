package gui.friends;

import api.Friend;
import client.ActivitiesApiService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * The class that controls the 'Friends' fxml screen.
 */
public class FriendsController {

    /**
     * Setting the spacing of the VBox.
     */
    private static final int SPACING = 17;

    /**
     * Setting the width of the VBox.
     */
    private static final int WIDTH = 250;

    /**
     * Setting the height of the VBox.
     */
    private static final int HEIGHT = 50;

    /**
     * Setting the width of the type column.
     */
    private static final int TYPE_WIDTH = 200;

    /**
     * @param friendPane the friends screen.
     */
    @FXML
    private AnchorPane friendPane;

    /**
     * @param result the result.
     */
    private List<Friend> result;

    /**
     * Method to add a friend.
     * @param event when the user clicks on the 'Add a Friend' button.
     * @throws Exception This is for any Exception.
     */
    public void addFriend(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/friends/CreateAFriend.fxml").toURI().toURL();
        AnchorPane createFriendActivity = FXMLLoader.load(url);
        friendPane.getChildren().setAll(createFriendActivity);
    }

    /**
     * Method to get a list of friends.
     * @return the list of friend.
     */
    public ObservableList<Friend> getFriendList() {
        ObservableList<Friend> actions = FXCollections.observableArrayList();
        actions.addAll(result);
        return actions;
    }

    /**
     * Method to get the friends from the server.
     * @param event when the user clicks on the 'See all your friends' button.
     * @throws Exception this is for any Exception encountered.
     */
    public void getFriends(final ActionEvent event) throws Exception {
        Scanner sc = new Scanner(new File("files/config/user.txt"));
        String username = sc.nextLine().trim();
        result = ActivitiesApiService.getFriend(username);

        if (result != null) {
            Stage stage = new Stage();
            stage.setTitle("GoGreen");
            stage.setResizable(false);
            TableView<Friend> table = new TableView<>();

            //Type column
            TableColumn<Friend, String> friendColumn = new TableColumn<>(
                    "Friend");
            friendColumn.setMinWidth(TYPE_WIDTH);
            friendColumn.setCellValueFactory(
                    new PropertyValueFactory<>("username"));

            table.setItems(getFriendList());
            table.getColumns().addAll(friendColumn);

            VBox layout = new VBox();
            Scene scene = new Scene(layout);
            layout.getChildren().addAll(table);

            stage.setScene(scene);
            stage.show();

        } else {
            Stage newStage = new Stage();
            newStage.setResizable(false);

            VBox layout = new VBox(SPACING);
            layout.setMinHeight(HEIGHT);
            layout.setMinWidth(WIDTH);

            Label label = new Label("Your friend list is empty.");
            layout.getChildren().add(label);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            newStage.setTitle("GoGreen");
            newStage.setScene(scene);
            newStage.show();
        }
    }

    /**
     * Method to go back to the main tab.
     * @param event when the user clicks on the 'back' button.
     * @throws Exception This is for any Exception.
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        friendPane.getChildren().setAll(mainPane);
    }
}
