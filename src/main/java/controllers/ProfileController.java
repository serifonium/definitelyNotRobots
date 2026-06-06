package controllers;

import com.example.definitelynotrobots.FitnessDAO;
import com.example.definitelynotrobots.FitnessGoal;
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

/**
 * Controller for the profile page.
 * */
public class ProfileController extends BaseController {

    /**
     * Text label for the user's name.
     * */
    @FXML
    public Label testname;

    /**
     * Text label for the user's username.
     * */
    public Label testusername;

    /**
     * Text label for error feedback.
     * */
    public Label errorText;

    /**
     * Button to log the user out of their account.
     * */
    public Button logOut;

    /**
     * Parent node for all elements.
     * */
    public HBox profileRoot;

    /**
     * Text label for the user's calorie goal.
     * */
    public Label calorieLabel;

    /**
     * Text label for the user's carbs goal.
     * */
    public Label carbLabel;

    /**
     * Text label for the user's fat goal.
     * */
    public Label fatLabel;

    /**
     * Text label for the user's protein goal.
     * */
    public Label proteinLabel;

    /**
     * DAO for all saved fitness goals.
     * */
    public final FitnessDAO fitnessDAO = new FitnessDAO();

    public void initialize() {
        init(profileRoot);
        SetName();
        SetUsername();

        FitnessGoal goal =
                fitnessDAO.getByUserID(UserAccountDAO.currentAccount.getID());

        if (goal != null) {
            calorieLabel.setText("Calories: " + goal.getCalories() + " kcal/day");
            carbLabel.setText("Carbs: " + goal.getCarbs()+ "g");
            fatLabel.setText("Fats: " + goal.getFats() + "g");
            proteinLabel.setText("Protein: " + goal.getProtein() + "g");
        } else {
            calorieLabel.setText("No calorie goal set");
            carbLabel.setText("No carb goal set");
            fatLabel.setText("No fat goal set");
            proteinLabel.setText("No protein goal set");
        }
    }

    /**
     * Set the user's name for the text label.
     * */
    public void SetName()
    {
        testname.setText("Name: " + UserAccountDAO.currentAccount.getFirstname() + " " + UserAccountDAO.currentAccount.getLastname()); //prints the welcome text
    }

    /**
     * Set the user's username for the text label.
     * */
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
}
