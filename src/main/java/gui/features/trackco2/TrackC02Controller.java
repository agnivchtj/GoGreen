package gui.features.trackco2;

import api.Stat;

import client.ActivitiesApiService;
import client.messages.ClientStat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
 * The class that controls the Track CO2 page.
 */
public class TrackC02Controller {
    /**
     * Setting the spacing of the VBox.
     */
    private static final int SPACING = 17;

    /**
     * Setting the width of the VBox.
     */
    private static final int VBOX_WIDTH = 250;

    /**
     * Setting the height of the VBox.
     */
    private static final int VBOX_HEIGHT = 50;

    /**
     * Setting the min width of the columns.
     */
    private static final int COL_WIDTH = 200;

    /**
     * @param trackCO2Pane the tracking CO2 screen.
     */
    @FXML
    private AnchorPane trackC02Pane;

    /**
     * @param checkbox the 'Show CO2' checkbox.
     */
    @FXML
    private CheckBox checkbox;

    /**
     * @param result the output object.
     */
    private Stat result;

    /**
     * @param resultList the list of stats.
     */
    private List<ClientStat> resultList;

    @FXML
    private Button disclaimerButton;

    /**
     * Get the statistics of the user.
     * @return the Stat object.
     */
    public ObservableList<Stat> getStatList() {
        ObservableList<Stat> stat = FXCollections.observableArrayList();
        stat.add(result);
        return stat;
    }

    /**
     * Get all the statistics including the user's friends.
     * @return the list of statistics.
     */
    public ObservableList<ClientStat> getStatisticsList() {
        ObservableList<ClientStat> statistics =
                FXCollections.observableArrayList();
        for (ClientStat s: resultList) {
            statistics.add(s);
        }
        return statistics;
    }

    /**
     * Method to get the statistics of a user.
     * @param event when the user clicks on the 'Track CO2 Activity' button.
     * @throws Exception This is for any Exception.
     */
    public void getStatistics(final ActionEvent event) throws Exception {
        if (checkbox.isSelected()) {
            Scanner sc = new Scanner(new File("files/config/user.txt"));
            String username = sc.nextLine().trim();
            resultList = ActivitiesApiService.getStats(username);

            if (resultList != null) {
                Stage stage = new Stage();
                stage.setTitle("GoGreen");
                stage.setResizable(false);

                //Username column
                TableColumn<ClientStat, String> usernameColumn =
                        new TableColumn<>("Username");
                usernameColumn.setMinWidth(COL_WIDTH);
                usernameColumn.setCellValueFactory(
                        new PropertyValueFactory<>("username"));

                //Points column
                TableColumn<ClientStat, String> ptsColumn = new TableColumn<>(
                        "Points");
                ptsColumn.setMinWidth(COL_WIDTH);
                ptsColumn.setCellValueFactory(
                        new PropertyValueFactory<>("points"));

                //CO2 Saved column
                TableColumn<ClientStat, String> savedColumn = new TableColumn<>(
                        "Kg of CO2 Saved");
                savedColumn.setMinWidth(COL_WIDTH);
                savedColumn.setCellValueFactory(
                        new PropertyValueFactory<>("saved"));

                TableView<ClientStat> table = new TableView<>();
                table.setItems(getStatisticsList());
                table.getColumns().addAll(
                        usernameColumn, ptsColumn, savedColumn);

                VBox layout = new VBox();

                Scene scene = new Scene(layout);
                stage.setScene(scene);
                layout.getChildren().addAll(table);
                stage.show();

            } else {
                Stage stg = new Stage();
                stg.setResizable(false);

                VBox layout = new VBox(SPACING);
                Scene scene = new Scene(layout);
                stg.setScene(scene);
                layout.setMinWidth(VBOX_WIDTH);
                layout.setMinHeight(VBOX_HEIGHT);

                Label label = new Label("There are no stats to show.");
                layout.getChildren().add(label);
                layout.setAlignment(Pos.CENTER);

                stg.setTitle("GoGreen");
                stg.show();
            }
        } else {
            Scanner sc = new Scanner(new File("files/config/user.txt"));
            String username = sc.nextLine().trim();
            result = ActivitiesApiService.getStat(username);

            if (result != null) {
                Stage stage = new Stage();
                stage.setTitle("GoGreen");

                //Username column
                TableColumn<Stat, String> userColumn = new TableColumn<>(
                        "Username");
                userColumn.setMinWidth(COL_WIDTH);
                userColumn.setCellValueFactory(
                        new PropertyValueFactory<>("username"));

                //CO2 Saved column
                TableColumn<Stat, String> savedColumn = new TableColumn<>(
                        "Kg of CO2 Saved");
                savedColumn.setMinWidth(COL_WIDTH);
                savedColumn.setCellValueFactory(
                        new PropertyValueFactory<>("saved"));

                //Points column
                TableColumn<Stat, String> pointsColumn = new TableColumn<>(
                        "Points");
                pointsColumn.setMinWidth(COL_WIDTH);
                pointsColumn.setCellValueFactory(
                        new PropertyValueFactory<>("points"));

                TableView<Stat> table = new TableView<>();
                table.setItems(getStatList());
                table.getColumns().addAll(
                        userColumn, pointsColumn, savedColumn);

                VBox layout = new VBox(SPACING);
                layout.getChildren().addAll(table);

                Scene scene = new Scene(layout);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } else {
                Stage stage = new Stage();
                stage.setTitle("GoGreen");

                VBox layout = new VBox(SPACING);
                layout.setMinHeight(VBOX_HEIGHT);
                layout.setMinWidth(VBOX_WIDTH);

                Scene newScene = new Scene(layout);

                Label label = new Label("Your statistics list is empty");
                layout.setAlignment(Pos.CENTER);
                layout.getChildren().addAll(label);

                stage.setScene(newScene);
                stage.setResizable(false);
                stage.show();
            }
        }
    }

    /**
     * return to the main menu.
     * @param event when the user clicks on the 'back' button.
     * @throws Exception This is for any Exception.
     */
    public void backToMain(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Main.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        trackC02Pane.getChildren().setAll(mainPane);
    }

    /**
     * Function to go to the disclaimer tab.
     * @param event when the user clicks on the 'disclaimer' button.
     * @throws Exception If exception is thrown.
     */
    public void disclaimerOnClick(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Disclaimer.fxml").toURI().toURL();
        AnchorPane mainPane = FXMLLoader.load(url);
        trackC02Pane.getChildren().setAll(mainPane);
    }


}
