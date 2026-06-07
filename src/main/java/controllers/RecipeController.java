package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.Recipe;
import com.example.definitelynotrobots.SavedRecipesDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.scene.control.TextField;

import javafx.scene.control.Label;

/**
 * Controller for each recipe page.
 * */
public class RecipeController extends BaseController {
    /**
     * Button to redirect to the home page.
     * */
    @FXML
    public Button homeButton;

    /**
     * Button to redirect to the saved recipes page.
     * */
    @FXML
    public Button saveRecipeButton;

    /**
     * Button to redirect to the profile page.
     * */
    @FXML
    public Button profileButton;

    /**
     * Button to redirect to the AI page.
     * */
    @FXML
    public Button aiButton;

    /**
     * Button to redirect to the pantry page.
     * */
    @FXML
    public Button pantryButton;

    /**
     * Button to redirect to the grocery list page.
     * */
    @FXML
    public Button groceryListButton;

    /**
     * Button to redirect to the saved recipes page.
     * */
    @FXML
    public Button savedRecipesButton;

    /**
     * Button to redirect to the fitness targets page.
     * */
    @FXML
    public Button fitnessTargetsButton;

    /**
     * Button to redirect to the preferences page.
     * */
    @FXML
    public Button preferencesButton;

    /**
     * Text label to display the title of the recipe.
     * */
    @FXML private Label recipeTitleLabel;

    /**
     * Text label to display the ingredients of the recipe.
     * */
    @FXML private Label ingredientsLabel;

    /**
     * Text label to display the method of the recipe.
     * */
    @FXML private Label methodLabel;

    /**
     * Text label to display the prep time of the recipe.
     * */
    @FXML private Label prepTimeLabel;

    /**
     * Text label to display the title of the recipe.
     * */
    @FXML private Label cookTimeLabel;

    /**
     * Text label to display the servings of the recipe.
     * */
    @FXML private Label servingsLabel;

    /**
     * DAO for all saved recipes.
     * */
    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();

    /**
     * The current recipe for the page.
     * */
    private Recipe recipe;

    /**
     * Parent node for all elements.
     * */
    public HBox RecipeRoot; //This Hbox is the main parent.
    public void initialize() throws IOException {
        ScaleRecipe(1);
    }

    /**
     * Changes all text fields for the current recipe.
     * */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;

        recipeTitleLabel.setText(recipe.getRecipeTitle());
        ingredientsLabel.setText(recipe.getIngredients());
        methodLabel.setText(recipe.getMethod());

        prepTimeLabel.setText("Prep: " + recipe.getPrepTime() + " min");
        cookTimeLabel.setText("Cook: " + recipe.getCookTime() + " min");
        servingsLabel.setText("Servings: " + recipe.getServings());
    }

    /**
     * Changes all text fields for the current recipe.
     * */
    @FXML
    private void saveRecipe() {
        if (recipe == null) return;

        recipe.setIsSaved(true);
        savedRecipesDAO.insertRecipe(recipe);

        saveRecipeButton.setText("Recipe Saved!");
    }

    private void ScaleRecipe(double scale) {
        RecipeRoot.setScaleX(scale); //Scales root parent
        RecipeRoot.setScaleY(scale);
    }

    public void goToHomeView() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToProfileView() throws IOException{
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profile-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToAIView() throws IOException{
        Stage stage = (Stage) aiButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("recipe-ai-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPantryView() throws IOException{
        Stage stage = (Stage) pantryButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("pantry-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToGroceryListView() throws IOException{
        Stage stage = (Stage) groceryListButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grocery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToSavedRecipesView() throws IOException{
        Stage stage = (Stage) savedRecipesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("saved-recipes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToFitnessTargets() throws IOException{
        Stage stage = (Stage) fitnessTargetsButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("fitness-targets-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToPreferences() throws IOException{
        Stage stage = (Stage) preferencesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("preferences-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }


}
