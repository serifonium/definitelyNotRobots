package com.example.definitelynotrobots;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class MainController {

    @FXML
    public Label testLabel;

    public void initialize() {
        testLabel.setText("Current User : " + UserAccountDAO.currentAccount.getUsername());
    }

    @FXML
    protected void onAIClick() throws Exception {
        Stage stage = (Stage) testLabel.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(
                HelloApplication.class.getResource("ai-view.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
