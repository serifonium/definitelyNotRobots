package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {

    @FXML
    public void initialize() throws IOException {
        SetName();
        SetSavedMeals();
        ScaleMainView(1.65);
    }

    public Label testLabel; //This label prints the "Welcome (user)!" text

    public void SetName()
    {
        testLabel.setText("Welcome, " + UserAccountDAO.currentAccount.getFirstname() + "!"); //prints the welcome text
    }

    public VBox SavedMeals; //This Vbox holds the list of saved meals.

    public void SetSavedMeals() throws IOException {
        //TODO LATER: Change how this works so it actually prints out saved meals in the database.
        SavedMeals.getChildren().clear();

        for (int i = 0; i < 8; i++) {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/definitelynotrobots/meal-template.fxml")
            );

            HBox meal = loader.load();

            meal.setStyle("-fx-background-color: #1e8648; -fx-padding: 10;");

            Label label = new Label("Item " + i);
            meal.getChildren().add(label);

            SavedMeals.getChildren().add(meal);
        }
    }

    public HBox mainRoot; //This Hbox is the main parent.
/// TODO LATER: ADD DESCRIPTION for ALL SCALE FUNCTIONS
    private void ScaleMainView(double scale) {
        mainRoot.setScaleX(scale); //Scales root parent
        mainRoot.setScaleY(scale);
    }

    public void goToGroceryList() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grocery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToPreferences() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("preferences-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToProfile() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToSavedRecipeView() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("saved-recipes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToAIPage() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("ai-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
