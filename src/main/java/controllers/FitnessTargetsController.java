package controllers;

import com.example.definitelynotrobots.FitnessDAO;
import com.example.definitelynotrobots.FitnessGoal;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for the fitness targets page.
 * */
public class FitnessTargetsController extends BaseController {
    /**
     * DAO for fitness goals.
     * */
    private final FitnessDAO fitnessDAO = new FitnessDAO();

    /**
     * Input text field for the user's calories goal.
     * */
    @FXML
    public TextField Caloriesgoal;

    /**
     * Input text field for the user's target weight.
     * */
    public TextField targetWeightField;

    /**
     * Input text field for the user's target fats.
     * */
    public TextField targetfats;

    /**
     * Input text field for the user's target carbs.
     * */
    public TextField targetcarbs;

    /**
     * Input text field for the user's target proteins.
     * */
    public TextField targetproteins;

    /**
     * Text label for error feedback.
     * */
    @FXML
    public Label errorText;

    /**
     * Button to save details.
     * */
    @FXML
    public Button savedetails;

    /**
     * Saves the user's input for new goals.
     * */
    public void onSaveDetails() throws IOException {

        double inputCalorie = Double.parseDouble(Caloriesgoal.getText());
        double Weight = Double.parseDouble(targetWeightField.getText());

        double inputProtein;
        double inputFat;
        double inputCarb;

        if (!targetfats.getText().trim().isEmpty()) {
            inputFat = Double.parseDouble(targetfats.getText());
        } else {
            inputFat = Weight;
        }

        if (!targetcarbs.getText().trim().isEmpty()) {
            inputCarb = Double.parseDouble(targetcarbs.getText());
        } else {
            inputCarb = inputCalorie / 4;
        }

        if (!targetproteins.getText().trim().isEmpty()) {
            inputProtein = Double.parseDouble(targetproteins.getText());
        } else {
            inputProtein = Weight * 2;
        }

        fitnessDAO.insertFitnessgoal(new FitnessGoal(UserAccountDAO.currentAccount.getID(), inputCalorie, inputCarb, inputFat, inputProtein));
        goToProfileView();
    }

    /**
     * Check for an existing fitness goal from the same user.
     * */
    public Boolean checkForExistingRecord() {
        FitnessGoal fitnessGoal = fitnessDAO.getByUserID(UserAccountDAO.currentAccount.getID());

        if(Objects.isNull(fitnessGoal)) return false;
        return true;
    }

    /**
     * Parent node for all elements.
     * */
    public HBox FitnessTargetRoot; //This Vbox is the main parent.
    // If you have any issues later check whether the root was changed to an HBox class.
    public void initialize() {
        init(FitnessTargetRoot);

        FitnessGoal existingRecord = fitnessDAO.getByUserID(UserAccountDAO.currentAccount.getID());

        if(!Objects.isNull(existingRecord)) {
            System.out.print(fitnessDAO.getByUserID(UserAccountDAO.currentAccount.getID()));
        }
    }


}
