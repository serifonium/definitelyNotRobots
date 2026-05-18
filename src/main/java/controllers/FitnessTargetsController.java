package controllers;

import com.example.definitelynotrobots.*;
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


    public void goToProfile() throws IOException {
        Stage stage = (Stage) Submit.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void initialize() {
        //ScaleMainView();
        System.out.println("Fitness page loaded");
    }


    public HBox FitnessTargetRoot; //This Hbox is the main parent.
    // If you have any issues later check whether the root was changed to an HBox class.


    private void ScaleMainView() {
        FitnessTargetRoot.setScaleX(1.6); //Scales root parent by 1.6
        FitnessTargetRoot.setScaleY(1.6);
    }
}
