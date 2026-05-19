package controllers;

import com.example.definitelynotrobots.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PreferencesController extends BaseController {
    public UserPreferenceDAO userPreferenceDAO = new UserPreferenceDAO();
    public UserAccountDAO userAccountDAO = new UserAccountDAO();

    @FXML
    public Button backButton;
    public TextArea preferenceList;
    public ChoiceBox<PreferenceTypeEnum> preferenceTypeField;
    public TextField preferenceContentField;
    public Label errorText;


    public void updatePreferenceList() {
        List<UserPreference> preferences = userPreferenceDAO.getByUserID(userAccountDAO.currentAccount.getID());

        StringBuilder contents = new StringBuilder();

        for (UserPreference preference : preferences) {
            contents.append(preference.getPreferenceType().toString() + ": " + preference.getContent() + "\n");
        }

        preferenceList.setText(contents.toString());
    }

    public HBox PreferencesRoot; //This Hbox is the main parent.
    // If you have any issues later check whether the root was changed to an HBox class.
    public void initialize() {
        init(PreferencesRoot);

        preferenceTypeField.getItems().setAll(PreferenceTypeEnum.values());

        updatePreferenceList();
    }

    public void onAdd() {
        PreferenceTypeEnum preferenceType = preferenceTypeField.getValue();
        String preferenceContent = preferenceContentField.getText();

        if (Objects.equals(preferenceType.toString(), "")) { errorText.setText("Preference type must be specified"); return; }
        if (Objects.equals(preferenceContent, "")) { errorText.setText("Preference content must be specified"); return; }
        errorText.setText("");

        userPreferenceDAO.insertPreference(new UserPreference(userAccountDAO.currentAccount.getID(), preferenceContent, preferenceType));

        updatePreferenceList();
    }
}
