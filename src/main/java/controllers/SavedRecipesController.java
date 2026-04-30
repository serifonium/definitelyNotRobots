package controllers;

import com.example.definitelynotrobots.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SavedRecipesController {
    @FXML
    public Button homeButton;
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
    public Button fitnessTargetsButton;
    @FXML
    public Button preferencesButton;
    @FXML
    private ListView<Recipe> recipeListView;
    @FXML
    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();
/*    @FXML
    public void initialize() {

        syncRecipeList();

        recipeListView.getSelectionModel().selectFirst();
        Recipe firstItem = recipeListView.getSelectionModel().getSelectedItem();
        if (firstItem != null) {
            selectRecipe(firstItem);
        }
    }*/

    public void goToAIView() throws IOException{
        Stage stage = (Stage) aiButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("recipe-ai-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void goToGroceryListView() throws IOException{
        Stage stage = (Stage) groceryListButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("grocery-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }public void goToSavedRecipesView() throws IOException{
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

    @FXML
    private void onSaveRecipe() {
        final Integer DEFAULT_USERID = null;
        final Image DEFAULT_IMAGE = null;
        final String DEFAULT_TITLE = "Recipe Title";
        final Integer DEFAULT_PREPTIME = 0;
        final Integer DEFAULT_COOKTIME = 0;
        final Integer DEFAULT_SERVINGS = 0;
        final Boolean DEAULT_ISSAVED = true;
        final String DEFAULT_INGREDIENTS = "ingredient, ingredient, ingredient";
        final String DEFAULT_METHOD = "1. method, 2. method, 3. method";
        Recipe newRecipe = new Recipe(SavedRecipesDAO.currentRecipe.getUserAccountIDRecipe(), DEFAULT_IMAGE, DEFAULT_TITLE, DEFAULT_PREPTIME, DEFAULT_COOKTIME, DEFAULT_SERVINGS, DEAULT_ISSAVED, DEFAULT_INGREDIENTS, DEFAULT_METHOD);

        savedRecipesDAO.insertRecipe(newRecipe);
    }



}
