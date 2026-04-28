package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.Recipe;
import com.example.definitelynotrobots.SavedRecipesDAO;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.awt.*;

public class RecipeController {

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
    private AiController aiController;
    @FXML
    public Recipe currentRecipe;
    @FXML
    public TextField recipeText;
    public void setCurrentRecipe(){currentRecipe = aiController.getGeneratedRecipe();}

    public Recipe getCurrentRecipe(){return currentRecipe;}

    private final OpenAIClient client = OpenAIOkHttpClient.fromEnv();
    public void initialize(){
        recipeText.setText(currentRecipe.getRecipeText());
    }

    public void goToHomeView() throws IOException {
        Stage stage = (Stage) homeButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }
    public void goToProfileView() throws IOException{
        Stage stage = (Stage) profileButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("preferences-view.fxml"));
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

    public void saveRecipe() throws IOException{
        Recipe recipe = aiController.getGeneratedRecipe();

        recipe.setIsSaved(true);

        new SavedRecipesDAO().insertRecipe(recipe);

        saveRecipeButton.setText("Recipe Saved!");
    }
    public void setAiController(AiController aiController) {
        this.aiController = aiController;
    }

}
