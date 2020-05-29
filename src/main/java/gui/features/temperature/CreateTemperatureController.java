package gui.features.temperature;

import client.ActivitiesApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * This class allows the user to create a new activity with the gui.
 */
public class CreateTemperatureController {

    /**
     * Setting the spacing of the VBox.
     */
    private static final int SPACING = 17;

    /**
     * Setting the width of the popup window.
     */
    private static final int MIN_WIDTH = 250;

    /**
     * Setting the height of the popup window.
     */
    private static final int MIN_HEIGHT = 50;

    /**
     * Setting the min height of the layout.
     */
    private static final int LAYOUT_HEIGHT = 100;

    /**
     * @param createTemperatureActivity the pane.
     */
    @FXML
    private AnchorPane createTemperatureActivity;

    /**
     * @param typeChoice the type of solar panel.
     */
    @FXML
    private MenuButton typeChoice;

    /**
     * @param textComments the comments written by the user.
     */
    @FXML
    private TextArea textComments;

    /**
     * Choice for Temperature.
     * @param event when the user clicks on the 'Temperature' option
     * @throws Exception This is for any Exception
     */
    public void temperature(final ActionEvent event) throws Exception {
        typeChoice.setText("Temperature");
    }

    /**
     * Sets an activity into the database.
     * @param event when the user clicks on the 'Create' button
     * @throws Exception This is for any Exception
     */
    public void setActivity(final ActionEvent event) throws Exception {
        Scanner sc = new Scanner(new File("files/config/user.txt"));
        String username = sc.nextLine().trim();
        String type = typeChoice.getText();
        String comments = textComments.getText();

        if (!type.equals("Select...")) {
            if (ActivitiesApiService.createActivity(username, type, comments)) {
                Stage newStage = new Stage();
                newStage.setResizable(false);
                newStage.setTitle("GoGreen");

                VBox newLayout = new VBox(SPACING);
                newLayout.setMinWidth(MIN_WIDTH);
                newLayout.setMinHeight(MIN_HEIGHT);
                Scene scene = new Scene(newLayout);
                newStage.setScene(scene);

                Label newLabel = new Label(
                        "The activity was successfully created");
                newLayout.setAlignment(Pos.CENTER);
                newLayout.getChildren().add(newLabel);

                newStage.show();
            } else {
                Stage newStage = new Stage();
                newStage.setTitle("GoGreen");
                newStage.setResizable(false);

                VBox newLayout = new VBox(SPACING);
                newLayout.setMinWidth(MIN_WIDTH);
                newLayout.setMinHeight(LAYOUT_HEIGHT);
                Scene scene = new Scene(newLayout);
                newStage.setScene(scene);

                Label newLabel = new Label(
                        "The activity could not be created. Please try again.");
                newLayout.setAlignment(Pos.CENTER);
                newLayout.getChildren().add(newLabel);

                newStage.show();
            }
        } else {
            Stage newStage = new Stage();
            newStage.setTitle("GoGreen");
            newStage.setResizable(false);

            VBox newLayout = new VBox(SPACING);
            newLayout.setMinWidth(MIN_WIDTH);
            newLayout.setMinHeight(LAYOUT_HEIGHT);
            Scene scene = new Scene(newLayout);
            newStage.setScene(scene);

            Label newLabel = new Label(
                    "Please select the activity type.");
            newLayout.setAlignment(Pos.CENTER);
            newLayout.getChildren().add(newLabel);

            newStage.show();
        }
    }

    /**
     * return to the previous screen.
     * @param event when the user clicks the 'Back' button
     * @throws Exception This is for any Exception
     */
    public void backToTemperature(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/temperature"
                        + "/Temperature.fxml").toURI().toURL();
        AnchorPane temperaturePane = FXMLLoader.load(url);
        createTemperatureActivity.getChildren().setAll(temperaturePane);
    }

    /**
     * returns to the main menu.
     * @param event when the user clicks 'Return to main menu' button
     * @throws Exception This is for any Exception
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        createTemperatureActivity.getChildren().setAll(mainPane);
    }
}
