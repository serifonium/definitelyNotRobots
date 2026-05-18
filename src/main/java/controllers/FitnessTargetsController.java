package controllers;

import com.example.definitelynotrobots.FitnessDAO;
import com.example.definitelynotrobots.Fitnessgoal;
import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FitnessTargetsController {
    private final FitnessDAO fitnessDAO = new FitnessDAO();

    @FXML
    public TextField CalorieInput;
    public TextField CarbsInput;
    public TextField FatInput;
    public TextField ProteinInput;
    @FXML
    public Button profileButton;
    @FXML
    public Button aiButton;
    @FXML
    public Button pantryButton;
    @FXML
    public Button groceryListButton;
    @FXML
    public Button savedRecipesButton;
    @FXML
    public Button fitnessTargetsButton;
    @FXML
    public Button preferencesButton;
    @FXML
    public Button homeButton;

    public void onSubmitClick() throws IOException {
        double inputCalorie = Double.parseDouble(CalorieInput.getText());
        double inputCarb = Double.parseDouble(CarbsInput.getText());
        double inputFat = Double.parseDouble(FatInput.getText());
        double inputProtein = Double.parseDouble(ProteinInput.getText());

        fitnessDAO.insertFitnessgoal(new Fitnessgoal(UserAccountDAO.currentAccount.getID(), inputCalorie, inputCarb, inputFat, inputProtein));
        goToProfileView();
    }

    public void initialize() {
        ScaleMainView(1.65);
    }


    public HBox FitnessTargetRoot; //This Vbox is the main parent.
    // If you have any issues later check whether the root was changed to an HBox class.


    private void ScaleMainView(double scale) {
        FitnessTargetRoot.setScaleX(scale); //Scales root parent by 1.6
        FitnessTargetRoot.setScaleY(scale);
    }

    public void goToHomeView() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToProfileView() throws IOException{
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToAIView() throws IOException{
        Stage stage = (Stage) aiButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("recipe-ai-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPantryView() throws IOException{
        Stage stage = (Stage) pantryButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantry-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToGroceryListView() throws IOException{
        Stage stage = (Stage) groceryListButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grocery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToSavedRecipesView() throws IOException{
        Stage stage = (Stage) savedRecipesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("saved-recipes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToFitnessTargets() throws IOException{
        Stage stage = (Stage) fitnessTargetsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fitness-targets-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPreferences() throws IOException{
        Stage stage = (Stage) preferencesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("preferences-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
