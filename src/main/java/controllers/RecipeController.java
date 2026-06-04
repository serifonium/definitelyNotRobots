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


public class RecipeController extends BaseController {
    @FXML
    public Button homeButton;
    @FXML
    public Button saveRecipeButton;
    @FXML
    public Button printButton;
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
    public TextField recipeText;
    @FXML private Label recipeTitleLabel;
    @FXML private Label ingredientsLabel;
    @FXML private Label methodLabel;
    @FXML private Label prepTimeLabel;
    @FXML private Label cookTimeLabel;
    @FXML private Label servingsLabel;

    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();

    private Recipe recipe;

    public void initialize() throws IOException {
        ScaleRecipe(1.6);
    }
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;

        recipeTitleLabel.setText(recipe.getRecipeTitle());
        ingredientsLabel.setText(recipe.getIngredients());
        methodLabel.setText(recipe.getMethod());

        prepTimeLabel.setText("Prep: " + recipe.getPrepTime() + " min");
        cookTimeLabel.setText("Cook: " + recipe.getCookTime() + " min");
        servingsLabel.setText("Servings: " + recipe.getServings());
    }
    @FXML
    private void saveRecipe() {
        if (recipe == null) return;

        recipe.setIsSaved(true);
        savedRecipesDAO.insertRecipe(recipe);

        saveRecipeButton.setText("Recipe Saved!");
    }

    public HBox RecipeRoot; //This Hbox is the main parent.
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
