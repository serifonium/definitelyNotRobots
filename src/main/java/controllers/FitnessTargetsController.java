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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FitnessTargetsController {
    private final FitnessDAO fitnessDAO = new FitnessDAO();

    @FXML
    public Button Submit;
    public TextField CalorieInput;
    public TextField CarbsInput;
    public TextField FatInput;
    public TextField ProteinInput;

    public void onSubmitClick() throws IOException {
        double inputCalorie = Double.parseDouble(CalorieInput.getText());
        double inputCarb = Double.parseDouble(CarbsInput.getText());
        double inputFat = Double.parseDouble(FatInput.getText());
        double inputProtein = Double.parseDouble(ProteinInput.getText());

        fitnessDAO.insertFitnessgoal(new Fitnessgoal(UserAccountDAO.currentAccount.getID(), inputCalorie, inputCarb, inputFat, inputProtein));
        goToProfile();
    }

    public void initialize() {
        ScaleMainView(1.65);
    }

    public VBox FitnessTargetRoot; //This Vbox is the main parent.
    // If you have any issues later check whether the root was changed to an HBox class.


    private void ScaleMainView(double scale) {
        FitnessTargetRoot.setScaleX(scale); //Scales root parent by 1.6
        FitnessTargetRoot.setScaleY(scale);
    }

    public void goToGroceryList() throws IOException {
        Stage stage = (Stage) FitnessTargetRoot.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grocery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToFitnessTargets() throws IOException{
        Stage stage = (Stage) FitnessTargetRoot.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fitness-targets-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPreferences() throws IOException {
        Stage stage = (Stage) FitnessTargetRoot.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("preferences-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToProfile() throws IOException {
        Stage stage = (Stage) FitnessTargetRoot.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToSavedRecipeView() throws IOException {
        Stage stage = (Stage) FitnessTargetRoot.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("saved-recipes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToAIPage() throws IOException {
        Stage stage = (Stage) FitnessTargetRoot.getScene().getWindow();
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("ai-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToHomeView() throws IOException {
        Stage stage = (Stage) FitnessTargetRoot.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
