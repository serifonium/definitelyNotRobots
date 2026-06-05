package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.Recipe;
import com.example.definitelynotrobots.UserAccountDAO;
import com.example.definitelynotrobots.SavedRecipesDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class MainController{

    @FXML
    public void initialize() throws IOException {
        SetName();
        ScaleMainView(1.6);
        int userId = UserAccountDAO.currentAccount.getID();
        Recipe recentRecipe = savedRecipesDAO.getFirstRecipeInList(userId);
        recipeTitle.setText(recentRecipe.getRecipeTitle());
        recipeIngredients.setText(recentRecipe.getIngredients());
        recipeMethod.setText(recentRecipe.getMethod());
        recipeServingsAndTime.setText("Servings: " + recentRecipe.getServings() + "| Cook Time: " + recentRecipe.getCookTime() + "| Prep Time: " + recentRecipe.getPrepTime());

    }

    public Label testLabel; //This label prints the "Welcome (user)!" text
    public Label recipeTitle;
    public Label recipeIngredients;
    public Label recipeMethod;
    public Label recipeServingsAndTime;
    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();

    public void SetName()
    {
        testLabel.setText("Welcome, " + UserAccountDAO.currentAccount.getFirstname() + "!"); //prints the welcome text
    }

    public HBox mainRoot; //This Hbox is the main parent.
/// TODO LATER: ADD DESCRIPTION for ALL SCALE FUNCTIONS
    private void ScaleMainView(double scale) {
        mainRoot.setScaleX(scale); //Scales root parent
        mainRoot.setScaleY(scale);
    }



    public void goToGroceryList() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grocery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToFitnessTargets() throws IOException{
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fitness-targets-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPreferences() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("preferences-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToProfile() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToSavedRecipeView() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("saved-recipes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToAIPage() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("ai-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToHomeView() throws IOException {
        Stage stage = (Stage) testLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
