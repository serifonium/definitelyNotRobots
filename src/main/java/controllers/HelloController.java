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

public class HelloController extends BaseController {
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
        UserAccountDAO.currentAccount = account;

        Stage stage = (Stage) signInButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void handleArrowNavigation(KeyEvent event) {

    }

    @FXML
    protected void onSignUpButtonClick() throws IOException {
        Stage stage = (Stage) signInButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("sign-up-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

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

    public HBox helloRoot; //This Hbox is the main parent.
    public void initialize() {
        init(helloRoot);
//        ScaleMainView(1.5);
    }



    private void ScaleMainView(double scale) {
        helloRoot.setScaleX(scale); //Scales root parent
        helloRoot.setScaleY(scale);
    }
}