package com.example.definitelynotrobots;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloController {
    private final UserAccountDAO userAccountDAO = new UserAccountDAO();

    @FXML
    public Button signInButton;
    public PasswordField passwordInput;
    public TextField usernameInput;
    public Label errorText;

    @FXML
    protected void onLoginButtonClick() throws IOException {
        String inputUsername = usernameInput.getText();
        String inputPassword = passwordInput.getText();

        // Handle user errors
        if(Objects.equals(inputUsername, "")) { errorText.setText("Username field is empty"); return; }
        if(Objects.equals(inputPassword, "")) { errorText.setText("Password field is empty"); return; }
        errorText.setText("");

        UserAccount account = userAccountDAO.queryDetails(inputUsername, inputPassword);
        if(Objects.isNull(account)) { errorText.setText("Details are incorrect"); return; }
        System.out.println(account);

        com.example.definitelynotrobots.UserAccountDAO.currentAccount = account;

        Stage stage = (Stage) signInButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        Stage stage = (Stage) signInButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
