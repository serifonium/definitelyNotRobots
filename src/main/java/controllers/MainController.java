package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.Recipe;
import com.example.definitelynotrobots.UserAccountDAO;
import com.example.definitelynotrobots.SavedRecipesDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the home page of the application.
 * */
public class MainController {

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

    /**
     * Text label to welcome the user (prints "Welcome (user)!").
     * */
    public Label welcomeLabel; //This label prints the "Welcome (user)!" text

    /**
     * Text label to display the current recipe title.
     * */
    public Label recipeTitle;

    /**
     * Text label to display the current recipe ingredients.
     * */
    public Label recipeIngredients;

    /**
     * Text label to display the current recipe method.
     * */
    public Label recipeMethod;

    /**
     * Text label to display the current recipe servings and time metrics.
     * */
    public Label recipeServingsAndTime;

    /**
     * DAO for all saved recipes.
     * */
    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();

    /**
     * Sets the welcomeLabel to welcome the user.
     * */
    public void SetName()
    {
        welcomeLabel.setText("Welcome, " + UserAccountDAO.currentAccount.getFirstname() + "!"); //prints the welcome text
    }

    /**
     * Parent node for all elements.
     * */
    public HBox mainRoot; //This Hbox is the main parent.
/// TODO LATER: ADD DESCRIPTION for ALL SCALE FUNCTIONS
    private void ScaleMainView(double scale) {
        mainRoot.setScaleX(scale); //Scales root parent
        mainRoot.setScaleY(scale);
    }

    public void goToGroceryList() throws IOException {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grocery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToFitnessTargets() throws IOException{
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fitness-targets-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPreferences() throws IOException {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("preferences-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToProfile() throws IOException {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToSavedRecipeView() throws IOException {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("saved-recipes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToAIPage() throws IOException {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        FXMLLoader fxmlLoader =
                new FXMLLoader(HelloApplication.class.getResource("ai-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToHomeView() throws IOException {
        Stage stage = (Stage) welcomeLabel.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
}
