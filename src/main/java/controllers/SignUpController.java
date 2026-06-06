package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.UserAccount;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the sign-up page.
 * */
public class SignUpController {
    /**
     * DAO for all saved user accounts.
     * */
    private final UserAccountDAO userAccountDAO = new UserAccountDAO();

    /**
     * Input text field for the username.
     * */
    public TextField usernameInput;

    /**
     * Input text field for the password.
     * */
    public PasswordField passwordInput;

    /**
     * Input text field for the first name.
     * */
    public TextField firstnameInput;

    /**
     * Input text field for the last name.
     * */
    public TextField lastnameInput;

    /**
     * Button to sign in with the inputted details.
     * */
    public Button signInButton;

    /**
     * Text label for error feedback.
     * */
    public Label errorText;

    /**
     * Attempt to sign the user up with the given details.
     * */
    public void onSignUpButtonClick() throws IOException {
        String inputUsername = usernameInput.getText();
        String inputPassword = passwordInput.getText();
        String inputFirstname = firstnameInput.getText();
        String inputLastname = lastnameInput.getText();

        // Handle user errors
        if(Objects.equals(inputUsername, "")) { errorText.setText("Username field is empty"); return; }
        if(Objects.equals(inputPassword, "")) { errorText.setText("Password field is empty"); return; }
        errorText.setText("");

        userAccountDAO.insertUser(new UserAccount(inputUsername, inputPassword, inputFirstname, inputLastname));
        goToSignIn();
    }

    /**
     * Redirect the user to the login page.
     * */
    public void goToSignIn() throws IOException {
        Stage stage = (Stage) signInButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    /**
     * Handle the user's arrow inputs to navigate.
     * */
    public void handleArrowNavigation(javafx.scene.input.KeyEvent event) {
        if(!event.getCode().getName().equals("Enter") && !event.getCode().getName().equals("Down") && !event.getCode().getName().equals("Up")) return;

        // get focused textbox
        Node source = (Node) event.getSource();
        Node focused = source.getScene().getFocusOwner();

        // get direction of arrow
        String keyCode = event.getCode().getName();
        if(keyCode.equals("Enter")) keyCode = "Down";

        // get next node
        Node nextNode = null;
        if(focused.getId().equals("usernameInput") && keyCode.equals("Down")) nextNode = passwordInput;
        if(focused.getId().equals("passwordInput") && keyCode.equals("Up")) nextNode = usernameInput;
        if(focused.getId().equals("firstnameInput") && keyCode.equals("Up")) nextNode = passwordInput;

        if(nextNode == null) return;

        nextNode.requestFocus();
    }

    /**
     * Parent node for all elements.
     * */
    public HBox signupRoot; //This Hbox is the main parent.
    @FXML
    public void initialize() throws IOException {
        ScaleMainView(1.6);
    }

    private void ScaleMainView(double scale) {
        signupRoot.setScaleX(scale); //Scales root parent
        signupRoot.setScaleY(scale);
    }
}
