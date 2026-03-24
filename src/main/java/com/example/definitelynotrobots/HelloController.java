package com.example.definitelynotrobots;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to the CAB302 Application!");
    }



    public void onJackButtonClick() {
        welcomeText.setText("Hi Im Jack");
    }

}
