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

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the login page of the application.
 * */
public class LoginController extends BaseController {
    /**
     * DAO for all user accounts.
     * */
    private final UserAccountDAO userAccountDAO = new UserAccountDAO();

    /**
     * Button to sign in.
     * */
    @FXML
    public Button signInButton;

    /**
     * Input text box for the user to input a password.
     * */
    public PasswordField passwordInput;

    /**
     * Input text box for the user to input a username.
     * */
    public TextField usernameInput;

    /**
     * Text label for error feedback.
     * */
    public Label errorText;

    /**
     * Attempt to log the user in with the given details.
     * */
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
        UserAccountDAO.currentAccount = account;

        Stage stage = (Stage) signInButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));

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

        if(nextNode == null) return;

        nextNode.requestFocus();
    }

    /**
     * Parent node for all elements.
     * */
    public HBox helloRoot; //This Hbox is the main parent.
    public void initialize() {
        init(helloRoot);
    }
}