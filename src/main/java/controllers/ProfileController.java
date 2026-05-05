package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    @FXML
    public Label testname;
    public Label testusername;
    public Label errorText;
    public Button logOut;

    @FXML
    private AnchorPane profilecontent;

    public void initialize() {
        SetName();
        SetUsername();
    }
    public void SetName()
    {
        testname.setText("Name: " + UserAccountDAO.currentAccount.getFirstname() + " " + UserAccountDAO.currentAccount.getLastname()); //prints the welcome text
    }

    public void SetUsername()
    {
        testusername.setText("username: " + UserAccountDAO.currentAccount.getUsername());
    }


    public void goToHelloController() throws IOException {
        Stage stage = (Stage) logOut.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToPage(String fxmlName) throws IOException {
        Stage stage = (Stage) errorText.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlName+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToGroceryList() throws IOException { goToPage("grocery-view"); }
    public void goToAIPage() throws IOException { goToPage("ai-view"); }
    public void goToPreferences() throws IOException { goToPage("preferences-view"); }
    public void goToFitnessTargets() throws IOException { goToPage("fitness-targets-view"); }
    public void goToSavedRecipeView() throws IOException { goToPage("saved-recipes-view"); }

}
