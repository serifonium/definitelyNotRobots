package controllers;

import com.example.definitelynotrobots.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class FitnessTargetsController {
    @FXML
    public Button Submit;


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
