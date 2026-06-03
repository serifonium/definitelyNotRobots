package controllers;

import com.example.definitelynotrobots.FitnessDAO;
import com.example.definitelynotrobots.FitnessGoal;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.Objects;

public class FitnessTargetsController extends BaseController {
    private final FitnessDAO fitnessDAO = new FitnessDAO();

    @FXML
    public TextField CalorieInput;
    public TextField CarbsInput;
    public TextField FatInput;
    public TextField ProteinInput;
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

    public void onSubmitClick() throws IOException {
        if(Objects.equals(CalorieInput.getText(), "")) { errorText.setText("Please enter a calorie goal."); return; }
        if(Objects.equals(CarbsInput.getText(), "")) { errorText.setText("Please enter a carbs goal."); return; }
        if(Objects.equals(FatInput.getText(), "")) { errorText.setText("Please enter a fat goal."); return; }
        if(Objects.equals(ProteinInput.getText(), "")) { errorText.setText("Please enter a protein goal."); return; }
        errorText.setText("");

        double inputCalorie = Double.parseDouble(CalorieInput.getText());
        double inputCarb = Double.parseDouble(CarbsInput.getText());
        double inputFat = Double.parseDouble(FatInput.getText());
        double inputProtein = Double.parseDouble(ProteinInput.getText());

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
