package controllers;

import com.example.definitelynotrobots.HelloApplication;
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
    private TextField chatbotInput;

    @FXML
    private TextArea chatbotOutput;

    private final OpenAIClient client = OpenAIOkHttpClient.fromEnv();

    @FXML
    protected void onChatbotInputButtonClick() {
        String userInput = chatbotInput.getText();

        if (userInput.isEmpty()) {
            chatbotOutput.setText("Type something first.");
            return;
        }

        try {
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .input(userInput)
                    .model("gpt-4o-mini")
                    .build();

            Response response = client.responses().create(params);

            String text = response.output().stream()
                    .flatMap(item -> item.message().stream())          // Optional → stream
                    .flatMap(msg -> msg.content().stream())            // list
                    .flatMap(content -> content.outputText().stream()) // Optional → stream
                    .map(t -> t.text())                                // NOW this works
                    .findFirst()
                    .orElse("No response");

            System.out.println("AI says: " + text);
            chatbotOutput.setText(text);

        } catch (Exception e) {
            e.printStackTrace();
            chatbotOutput.setText("Error: " + e.getMessage());
        }
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
    }public void goToSavedRecipesView() throws IOException{
        Stage stage = (Stage) savedRecipesButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("saved-recipes-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
    }

    public void saveRecipe() throws IOException{
        saveRecipeButton.setText("Recipe saved!");
    }
}
