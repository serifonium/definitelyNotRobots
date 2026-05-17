package controllers;

import com.example.definitelynotrobots.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PreferencesController {
    @FXML
    public Button backButton;

    public void initialize() {
        ScaleMainView(1.65);
    }

    public VBox PreferencesRoot; //This Vbox is the main parent.
    // If you have any issues later check whether the root was changed to an HBox class.


    private void ScaleMainView(double scale) {
        PreferencesRoot.setScaleX(scale); //Scales root parent
        PreferencesRoot.setScaleY(scale);
    }

    public void goToHomeView() throws IOException {
        Stage stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
