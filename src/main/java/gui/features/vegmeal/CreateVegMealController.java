package gui.features.vegmeal;

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
public class CreateVegMealController {
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
     * Setting the min height.
     */
    private static final int LAYOUT_HEIGHT = 100;

    /**
     * @param createVegMealActivity the pane.
     */
    @FXML
    private AnchorPane createVegMealActivity;

    /**
     * @param typeChoice the type of meal.
     */
    @FXML
    private MenuButton typeChoice;

    /**
     * @param textComments the comments written by the user.
     */
    @FXML
    private TextArea textComments;

    /**
     * Choice for vegetarian meal.
     * @param event when the user clicks on the 'Vegetarian meal' option
     * @throws Exception This is for any Exception
     */
    public void vgtrnMeal(final ActionEvent event) throws Exception {
        typeChoice.setText("Vegetarian meal");
    }

    /**
     * Choice for vegetarian meal.
     * @param event when the user clicks on the 'Vegan meal' option
     * @throws Exception This is for any Exception
     */
    public void vgnMeal(final ActionEvent event) throws Exception {
        typeChoice.setText("Vegan meal");
    }

    /**
     * Sets an activity into the database.
     * @param event when the user clicks on the 'Create' button
     * @throws Exception This is for any Exception
     */
    public void setActivity(final ActionEvent event) throws Exception {
        String type = typeChoice.getText();
        String comments = textComments.getText();
        Scanner sc = new Scanner(new File("files/config/user.txt"));
        String username = sc.nextLine().trim();
        boolean result = ActivitiesApiService.createActivity(
                username, type, comments);

        if (!type.equals("Select...")) {
            if (result) {
                Stage newStage = new Stage();
                newStage.setResizable(false);
                newStage.setTitle("GoGreen");

                VBox newLayout = new VBox(SPACING);
                newLayout.setMinHeight(MIN_HEIGHT);
                newLayout.setMinWidth(MIN_WIDTH);

                Label newLabel = new Label(
                        "The activity was successfully created");
                newLayout.setAlignment(Pos.CENTER);
                newLayout.getChildren().add(newLabel);

                Scene scene = new Scene(newLayout);
                newStage.setScene(scene);
                newStage.show();

            } else {
                Stage stage = new Stage();
                stage.setTitle("GoGreen");

                VBox newLayout = new VBox(SPACING);
                newLayout.setMinWidth(MIN_WIDTH);
                newLayout.setMinHeight(LAYOUT_HEIGHT);
                Scene scene = new Scene(newLayout);

                Label newLabel = new Label(
                        "The activity could not be created. Please try again.");
                newLayout.setAlignment(Pos.CENTER);
                newLayout.getChildren().add(newLabel);

                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }
        } else {
            Stage stage = new Stage();
            stage.setResizable(false);

            VBox newLayout = new VBox(SPACING);
            newLayout.setMinWidth(MIN_WIDTH);
            newLayout.setMinHeight(LAYOUT_HEIGHT);
            Scene scene = new Scene(newLayout);
            stage.setScene(scene);

            Label newLabel = new Label(
                    "Please select an activity type.");
            newLayout.setAlignment(Pos.CENTER);
            newLayout.getChildren().add(newLabel);

            stage.setTitle("GoGreen");
            stage.show();
        }
    }

    /**
     * return to the previous vegetarian meal screen.
     * @param event when the user clicks the 'Back' button
     * @throws Exception This is for any Exception
     */
    public void backToVegMeal(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/"
                        + "vegmeal/VegetarianMeal.fxml").toURI().toURL();
        AnchorPane vegMealPane = FXMLLoader.load(url);
        createVegMealActivity.getChildren().setAll(vegMealPane);
    }

    /**
     * returns to the main menu.
     * @param event when the user clicks 'Return to main menu' button
     * @throws Exception This is for any Exception
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        createVegMealActivity.getChildren().setAll(mainPane);
    }
}
