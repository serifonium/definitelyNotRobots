package controllers;

import com.example.definitelynotrobots.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Base class which contains common functions for controllers.
 */
public abstract class BaseController {
    /**
     * Parent container for all objects in the page.
     */
    protected Node parentNode;

    /**
     * Initializes variables and runs initial functions.
     */
    protected void init(Node parentNode) {
        this.parentNode = parentNode;

        ScaleMainView();
    }

    /**
     * Scales root parent by 1.6.
     */
    private void ScaleMainView() {
        parentNode.setScaleX(1.6);
        parentNode.setScaleY(1.6);
    }

    /**
     * Loads a new scene.
     * @param fxmlFile Name of fxml file (without .fxml extension).
     */
    protected void loadScene(String fxmlFile) throws IOException {
        Stage stage = (Stage) parentNode.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile+".fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    public void goToHomeView() throws IOException { loadScene("main-view"); }
    public void goToAIView() throws IOException { loadScene("ai-view"); }
    public void goToPantryView() throws IOException { loadScene("pantry-view");}
    public void goToGroceryListView() throws IOException { loadScene("grocery-view"); }
    public void goToPreferencesView() throws IOException { loadScene("preferences-view"); }
    public void goToFitnessTargetsView() throws IOException { loadScene("fitness-targets-view"); }
    public void goToSavedRecipesView() throws IOException { loadScene("saved-recipes-view"); }
    public void goToProfileView() throws IOException { loadScene("Profile-view"); }
    public void goToLoginView() throws IOException { loadScene("hello-view"); }
    public void goToSignInView() throws IOException { loadScene("sign-in-view"); }
}
