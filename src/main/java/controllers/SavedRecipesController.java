package controllers;

import com.example.definitelynotrobots.HelloApplication;
import com.example.definitelynotrobots.Recipe;
import com.example.definitelynotrobots.SavedRecipesDAO;
import com.example.definitelynotrobots.UserAccountDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SavedRecipesController extends BaseController {

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
    public Button homeButton;

    @FXML private ListView<Recipe> recipeListView;

    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();

    @FXML
    public void initialize() {
        //Load saved recipes for current user
        int userId = UserAccountDAO.currentAccount.getID();
        List<Recipe> recipes = savedRecipesDAO.getSavedRecipesForUser(userId);

        //Populate the list
        recipeListView.getItems().setAll(recipes);

        //Display recipe titles
        recipeListView.setCellFactory(list -> new ListCell<>() {
            @Override
            protected void updateItem(Recipe recipe, boolean empty) {
                super.updateItem(recipe, empty);
                setText(empty || recipe == null ? "" : recipe.getRecipeTitle());
            }
        });

        //Enable Double-click to open recipe
        recipeListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Recipe selected = recipeListView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    openRecipe(selected);
                }
            }
        });
    }

    private void openRecipe(Recipe recipe) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    HelloApplication.class.getResource("recipe-view.fxml")
            );

            Stage stage = (Stage) recipeListView.getScene().getWindow();

            Scene scene = new Scene(loader.load());

            // Pass recipe to controller
            RecipeController controller = loader.getController();
            controller.setRecipe(recipe);

            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
