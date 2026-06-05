package controllers;

import com.example.definitelynotrobots.FitnessDAO;
import com.example.definitelynotrobots.FitnessGoal;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

public class FitnessTargetsController extends BaseController {
    private final FitnessDAO fitnessDAO = new FitnessDAO();
    @FXML
    public TextField Caloriesgoal;
    public TextField targetWeightField;
    public TextField targetfats;
    public TextField targetcarbs;
    public TextField targetproteins;
    @FXML
    public Button profileButton;
    @FXML
    public Button aiButton;
    @FXML
    public Button pantryButton;
    @FXML
    public Button groceryListButton;
    @FXML
    public Button savedRecipesButton;
    @FXML
    public Button fitnessTargetsButton;
    @FXML
    public Button preferencesButton;
    @FXML
    public Button homeButton;
    @FXML
    public Label errorText;
    @FXML
    public Button savedetails;

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

    public Boolean checkForExistingRecord() {
        FitnessGoal fitnessGoal = fitnessDAO.getByUserID(UserAccountDAO.currentAccount.getID());

        if(Objects.isNull(fitnessGoal)) return false;
        return true;
    }

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
