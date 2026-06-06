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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller for the saved recipes page.
 * */
public class SavedRecipesController extends BaseController {

    /**
     * Controller for the saved recipes page.
     * */
    @FXML
    private ListView<Recipe> recipeListView;

    /**
     * DAO for all saved recipes.
     * */
    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();

    /**
     * Parent node for all elements.
     * */
    public HBox RecipesRoot;
    @FXML
    public void initialize() {
        init(RecipesRoot);

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

    /**
     * Open a saved recipe.
     * */
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
