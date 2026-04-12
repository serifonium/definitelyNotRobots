package controllers;

import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class MainController {

    @FXML
    public Label testLabel;

    public void initialize() {
        testLabel.setText("Current User: " + UserAccountDAO.currentAccount.getUsername());
    }


}
