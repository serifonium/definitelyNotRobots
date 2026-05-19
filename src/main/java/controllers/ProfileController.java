package controllers;

import com.example.definitelynotrobots.FitnessDAO;
import com.example.definitelynotrobots.Fitnessgoal;
import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController extends BaseController {
    @FXML
    public Label testname;
    public Label testusername;
    public Label errorText;
    public Button logOut;
    public HBox profileRoot;
    public Label calorieLabel;
    public Label carbLabel;
    public Label fatLabel;
    public Label proteinLabel;

    public final FitnessDAO fitnessDAO = new FitnessDAO();

    public void initialize() {
        init(profileRoot);
        SetName();
        SetUsername();

        Fitnessgoal goal =
                fitnessDAO.getByUserID(UserAccountDAO.currentAccount.getID());

        if (goal != null) {
            calorieLabel.setText("Calories: " + goal.getCalories());
            carbLabel.setText("Carbs: " + goal.getCarbs());
            fatLabel.setText("Fats: " + goal.getFats());
            proteinLabel.setText("Protein: " + goal.getProtein());
        } else {
            calorieLabel.setText("No calorie goal set");
            carbLabel.setText("No carb goal set");
            fatLabel.setText("No fat goal set");
            proteinLabel.setText("No protein goal set");
        }
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
        Stage stage = (Stage) testname.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlName+".fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

//    public void goToGroceryList() throws IOException { goToPage("grocery-view"); }
//    public void goToAIPage() throws IOException { goToPage("ai-view"); }
//    public void goToHomeView() throws IOException { goToPage("main-view"); }
//    public void goToProfile() throws IOException { goToPage("Profile-view"); }
//    public void goToPreferences() throws IOException { goToPage("preferences-view"); }
//    public void goToFitnessTargets() throws IOException { goToPage("fitness-targets-view"); }
//    public void goToSavedRecipeView() throws IOException { goToPage("saved-recipes-view"); }

}
