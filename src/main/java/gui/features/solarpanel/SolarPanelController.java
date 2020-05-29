package gui.features.solarpanel;

import api.Activity;
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
 * The class that controls the contents of the vegetarian Meal page.
 */
public class SolarPanelController {

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
     * Setting the column weight.
     */
    private static final int COL_WIDTH = 200;

    /**
     * variable for the vegetarian meal screen.
     * @param vegetarianMealPane the vegetarian meal screen
     */
    @FXML
    private AnchorPane solarPanelPane;

    /**
     * variable for the result.
     * @param result the result
     */
    private List<Activity> result;

    /**
     *  Creates a new activity.
     * @param event the user clicks on the 'Create an activity' button
     * @throws Exception This is for any Exception
     */
    public void createActivity(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/features/"
                + "solarpanel/CreateSolarPanelActivity.fxml").toURI().toURL();
        AnchorPane createSolarPanelActivity = FXMLLoader.load(url);
        solarPanelPane.getChildren()
                .setAll(createSolarPanelActivity);
    }

    /**
     * Get only the vegan meal and solar panel.
     * @return the activity list.
     */
    public ObservableList<Activity> getActivityList() {
        ObservableList<Activity> actions = FXCollections.observableArrayList();
        for (Activity a: result) {
            if (a.getType().equals("Solar panel")) {
                actions.add(a);
            }
        }
        return actions;
    }

    /**
     * Get an activity.
     * @param event the user clicks on the 'Get an activity' button
     * @throws Exception This is for any Exception
     */
    public void getActivity(final ActionEvent event) throws Exception {
        Scanner sc = new Scanner(new File("files/config/user.txt"));
        String username = sc.nextLine().trim();
        result = ActivitiesApiService.getActivity(username);

        if (result != null) {
            Stage stage = new Stage();
            stage.setTitle("GoGreen");
            stage.setResizable(false);


            //Type column
            TableColumn<Activity, String> typeColumn = new TableColumn<>(
                    "Type of solar panel");
            typeColumn.setMinWidth(COL_WIDTH);
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

            //Comment column
            TableColumn<Activity, String> commentColumn = new TableColumn<>(
                    "Comment");
            commentColumn.setMinWidth(COL_WIDTH);
            commentColumn.setCellValueFactory(new PropertyValueFactory<>(
                    "comment"));

            //Timestamp column
            TableColumn<Activity, String> timestampColumn = new TableColumn<>(
                    "Timestamp");
            timestampColumn.setMinWidth(COL_WIDTH);
            timestampColumn.setCellValueFactory(
                    new PropertyValueFactory<>("timestamp"));

            TableView<Activity> table = new TableView<>();

            table.setItems(getActivityList());
            table.getColumns().addAll(typeColumn,
                    commentColumn, timestampColumn);

            VBox layout = new VBox();
            layout.getChildren().addAll(table);

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

        } else {
            Stage newStage = new Stage();
            newStage.setResizable(false);

            VBox layout = new VBox(SPACING);
            layout.setMinWidth(WIDTH);
            layout.setMinHeight(HEIGHT);

            Label label = new Label("Your activity list is empty.");
            layout.getChildren().add(label);
            layout.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout);
            newStage.setTitle("GoGreen");
            newStage.setScene(scene);
            newStage.show();
        }
    }

    /**
     * return to the main menu.
     * @param event when the user clicks on the 'back' button
     * @throws Exception This is for any Exception
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        solarPanelPane.getChildren().setAll(mainPane);
    }
}
