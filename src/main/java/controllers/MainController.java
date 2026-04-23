package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController {

    @FXML
    public Label testLabel; //This label prints the "Welcome (user)!" text
    public VBox SavedMeals; //This Vbox holds the list of saved meals.

    public void initialize() {
        SetName();
        SetSavedMeals();
    }
    public void SetName()
    {
        testLabel.setText("Welcome, " + UserAccountDAO.currentAccount.getUsername() + "!"); //prints the welcome text
    }
    public void SetSavedMeals()
    { //TODO LATER: Change how this works so it actually prints out saved meals in the database.
        for (int i = 0; i < 8; i++) { //currently just makes a set of empty boxes
            VBox meal = new VBox();
            meal.setStyle("-fx-background-color: #1e8648; -fx-padding: 10;");
            meal.getChildren().add(new Label("Item " + i));

            SavedMeals.getChildren().add(meal);
        }
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

    public void goToFitnessTargets() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fitness-targets-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToRecipeView() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("recipe-view.fxml"));
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
