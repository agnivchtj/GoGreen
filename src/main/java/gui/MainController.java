package gui;

import api.Stat;
import client.ActivitiesApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.Scanner;

/**
 * This class enables interactivity on the main screen.
 */
public class MainController {
    /**
     * The minimum pts required to get bronze medal.
     */
    private static final int MIN_BRONZE = 100;

    /**
     * The minimum pts required to get silver medal.
     */
    private static final int MIN_SILVER = 250;

    /**
     * The minimum pts required to get gold medal.
     */
    private static final int MIN_GOLD = 500;

    /**
     * Setting the spacing of the VBox.
     */
    private static final int SPACING = 17;

    /**
     * Setting the width of the popup window.
     */
    private static final int MIN_WIDTH = 380;

    /**
     * Setting the height of the popup window.
     */
    private static final int MIN_HEIGHT = 100;

    /**
     * This is the minimum contribution achievement.
     */
    private static final double FIRST_LEVEL = 5.00;

    /**
     * This is the second contribution achievement.
     */
    private static final double SECOND_LEVEL = 11.00;

    /**
     * This is the third contribution achievement.
     */
    private static final double THIRD_LEVEL = 20.00;

    /**
     * This is the fourth contribution achievement.
     */
    private static final double FOURTH_LEVEL = 35.00;

    /**
     * This is the fifth contribution achievement.
     */
    private static final double FIFTH_LEVEL = 55.00;

    /**
     * This is the sixth contribution achievement.
     */
    private static final double SIXTH_LEVEL = 100.00;

    /**
     * variable for the main screen.
     *
     * @param mainPane the main screen
     */
    @FXML
    private AnchorPane mainPane;

    /**
     * This is the achievements/notifications button.
     */
    @FXML
    private SplitMenuButton accomplishments;

    /**
     * @param result the output object.
     */
    private Stat result;

    /**
     * Logout button.
     *
     * @param event when user clicks on the 'logout' button
     * @throws Exception This is for any Exception
     */
    public void logout(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/Login.fxml").toURI().toURL();
        AnchorPane loginPane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(loginPane);
    }

    /**
     * vegetarian meal button.
     *
     * @param event when user clicks on the 'veg meal' button
     * @throws Exception This is for any Exception
     */
    public void vegetarianMeal(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/vegmeal/"
                        + "VegetarianMeal.fxml").toURI().toURL();
        AnchorPane vegMealPane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(vegMealPane);
    }

    /**
     * solar panel button.
     *
     * @param event when user clicks on the 'solar panel' button
     * @throws Exception This is for any Exception
     */
    public void solarPanel(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/solarpanel/"
                        + "SolarPanel.fxml").toURI().toURL();
        AnchorPane solarPanelPane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(solarPanelPane);
    }

    /**
     * local produce button.
     *
     * @param event when user clicks on the 'local produce' button
     * @throws Exception This is for any Exception
     */
    public void localProduce(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/localproduce/"
                        + "LocalProduce.fxml").toURI().toURL();
        AnchorPane localProducePane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(localProducePane);
    }

    /**
     * bike button.
     *
     * @param event when user clicks on the 'bike' button
     * @throws Exception This is for any Exception
     */
    public void bike(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/bike/bike.fxml").toURI().toURL();
        AnchorPane bikePane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(bikePane);
    }

    /**
     * public transport button.
     *
     * @param event when user clicks on the 'public transport' button
     * @throws Exception This is for any Exception
     */
    public void publicTransport(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/publictransport/"
                        + "PublicTransport.fxml").toURI().toURL();
        AnchorPane publicTransportPane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(publicTransportPane);
    }

    /**
     * temperature button.
     *
     * @param event when user clicks on the 'temperature' button
     * @throws Exception This is for any Exception
     */
    public void temperature(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/temperature/"
                        + "Temperature.fxml").toURI().toURL();
        AnchorPane temperaturePane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(temperaturePane);
    }

    /**
     * Method to move to the friend tab.
     *
     * @param event when the user clicks on the 'Play with Friends!' button
     * @throws Exception This is for any Exception
     */
    public void friends(final ActionEvent event) throws Exception {
        URL url = new File("src/main/java/gui/friends/"
                + "Friends.fxml").toURI().toURL();
        AnchorPane friendPane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(friendPane);
    }


    /**
     * track co2 button.
     *
     * @param event when the user clicks on the 'Track CO2' button
     * @throws Exception This is for any Exception
     */
    public void trackCO2(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/features/trackco2/"
                        + "TrackC02.fxml").toURI().toURL();
        AnchorPane trackC02Pane = FXMLLoader.load(url);
        mainPane.getChildren().setAll(trackC02Pane);
    }

    /**
     * badges button.
     *
     * @param event when the user clicks on the 'Badges' button
     * @throws Exception This is for any Exception
     */
    public void badges(final ActionEvent event) throws Exception {
        Scanner sc = new Scanner(new File("files/config/user.txt"));
        String username = sc.nextLine().trim();
        result = ActivitiesApiService.getStat(username);
        int score = result.getPoints();

        if (score >= MIN_BRONZE && score < MIN_SILVER) {
            URL url = new File(
                    "src/main/java/gui/features/badges/Bronze.fxml").toURI().toURL();
            AnchorPane badgesBronze = FXMLLoader.load(url);
            mainPane.getChildren().setAll(badgesBronze);

        } else if (score >= MIN_SILVER && score < MIN_GOLD) {
            URL url = new File(
                    "src/main/java/gui/features/badges/Silver.fxml").toURI().toURL();
            AnchorPane badgesSilver = FXMLLoader.load(url);
            mainPane.getChildren().setAll(badgesSilver);

        } else if (score >= MIN_GOLD) {
            URL url = new File(
                    "src/main/java/gui/features/badges/Gold.fxml").toURI().toURL();
            AnchorPane badgesGold = FXMLLoader.load(url);
            mainPane.getChildren().setAll(badgesGold);
        } else {
            URL url = new File(
                    "src/main/java/gui/features/badges/Null.fxml").toURI().toURL();
            AnchorPane badgesNull = FXMLLoader.load(url);
            mainPane.getChildren().setAll(badgesNull);
        }
    }

    /**
     * @param event - to open and close the members pane.
     * @throws Exception - in case it fails, throw error
     */
    public void members(final ActionEvent event) throws Exception {
        URL url = new File(
                "src/main/java/gui/members/members.fxml").toURI().toURL();
        AnchorPane membersController = FXMLLoader.load(url);
        mainPane.getChildren().setAll(membersController);
    }

    /**
     * Displays a popup talking about the achievement.
     * @param contribution the amount of CO2 saved (in kg)
     */
    private static void displayAchievement(final double contribution) {
        Stage stage = new Stage();
        stage.setTitle("GoGreen");
        int score = (int) contribution;

        VBox layout = new VBox(SPACING);
        layout.setMinWidth(MIN_WIDTH);
        layout.setMinHeight(MIN_HEIGHT);
        layout.setAlignment(Pos.CENTER);

        Label firstlabel = new Label("Congratulations!");
        Label secondlabel = new Label("You have saved over "
                + score + " kg of CO2 so far through your activities!");
        Label thirdlabel = new Label("Keep it up!");
        layout.getChildren().addAll(firstlabel, secondlabel, thirdlabel);

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Adds the achievement as a notification.
     * @param achievement The menu item that we are passing
     * @param contribution the amount of CO2 saved (in kg)
     */
    private static void notification(
            final MenuItem achievement, final double contribution) {
        int score = (int) contribution;
        achievement.setText("Congrats! You have saved over "
                + score + " kg...");
        achievement.setOnAction(e -> {
            displayAchievement(contribution);
        });
    }

    /**
     * The achievements/notifications button.
     * @param event when the user clicks on the 'Achievements' button
     * @throws Exception This is for any Exception
     */
    public void achievements(final ActionEvent event) throws Exception {
        Scanner sc = new Scanner(new File("files/config/user.txt"));
        String username = sc.nextLine().trim();
        result = ActivitiesApiService.getStat(username);

        double contribution = result.getSaved();

        if (contribution >= FIRST_LEVEL && contribution < SECOND_LEVEL) {
            MenuItem first = new MenuItem();
            notification(first, FIRST_LEVEL);
            accomplishments.getItems().setAll(first);
        }
        if (contribution >= SECOND_LEVEL && contribution < THIRD_LEVEL) {
            MenuItem first = new MenuItem();
            MenuItem second = new MenuItem();
            notification(first, FIRST_LEVEL);
            notification(second, SECOND_LEVEL);
            accomplishments.getItems().setAll(second, first);
        }
        if (contribution >= THIRD_LEVEL && contribution < FOURTH_LEVEL) {
            MenuItem first = new MenuItem();
            notification(first, FIRST_LEVEL);
            MenuItem second = new MenuItem();
            notification(second, SECOND_LEVEL);
            MenuItem third = new MenuItem();
            notification(third, THIRD_LEVEL);
            accomplishments.getItems().setAll(third, second, first);
        }
        if (contribution >= FOURTH_LEVEL && contribution < FIFTH_LEVEL) {
            MenuItem first = new MenuItem();
            notification(first, FIRST_LEVEL);
            MenuItem second = new MenuItem();
            notification(second, SECOND_LEVEL);
            MenuItem third = new MenuItem();
            notification(third, THIRD_LEVEL);
            MenuItem fourth = new MenuItem();
            notification(fourth, FOURTH_LEVEL);
            accomplishments.getItems().setAll(fourth, third, second, first);
        }
        if (contribution >= FIFTH_LEVEL && contribution < SIXTH_LEVEL) {
            MenuItem first = new MenuItem();
            MenuItem second = new MenuItem();
            MenuItem third = new MenuItem();
            notification(first, FIRST_LEVEL);
            notification(second, SECOND_LEVEL);
            notification(third, THIRD_LEVEL);
            MenuItem fourth = new MenuItem();
            MenuItem fifth = new MenuItem();
            notification(fourth, FOURTH_LEVEL);
            notification(fifth, FIFTH_LEVEL);
            accomplishments.getItems().setAll(
                    fifth, fourth, third, second, first);
        }
        if (contribution >= SIXTH_LEVEL) {
            MenuItem first = new MenuItem();
            notification(first, FIRST_LEVEL);
            MenuItem second = new MenuItem();
            notification(second, SECOND_LEVEL);
            MenuItem third = new MenuItem();
            notification(third, THIRD_LEVEL);
            MenuItem fourth = new MenuItem();
            notification(fourth, FOURTH_LEVEL);
            MenuItem fifth = new MenuItem();
            notification(fifth, FIFTH_LEVEL);
            MenuItem sixth = new MenuItem();
            notification(sixth, SIXTH_LEVEL);
            accomplishments.getItems().setAll(
                    sixth, fifth, fourth, third, second, first);
        }
    }
}
