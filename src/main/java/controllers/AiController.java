package controllers;

import com.example.definitelynotrobots.Recipe;
import com.example.definitelynotrobots.SavedRecipesDAO;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import com.example.definitelynotrobots.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;

public class AiController {
    @FXML
    public Button saveRecipeButton;
    @FXML
    private TextArea chatbotInput;

    @FXML
    private TextArea chatbotOutput;
    @FXML
    public String generatedRecipeText;
    @FXML
    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();

    private final OpenAIClient client = OpenAIOkHttpClient.fromEnv();

    @FXML
    public String onChatbotInputButtonClick() {
        String userInput = chatbotInput.getText();

        if (userInput.isEmpty()) {
            chatbotOutput.setText("Type something first.");
            return null;
        }

        try {
            ResponseCreateParams params = ResponseCreateParams.builder()
                    .input("""
                            You are Not-A-Chef, a friendly food assistant.
                            
                            Rules:
                            - Always answer as if the topic is about food, cooking, recipes, ingredients, meal planning, nutrition, or kitchen help.
                            - If the user asks something unrelated, politely redirect it back to food.
                            - Keep answers practical and easy to follow.
                            - Do not give medical, allergy, or diet advice as guaranteed facts.
                            - If allergies, illness, pregnancy, medication, or serious health issues are mentioned, tell the user to check with a qualified professional.
                            - Keep a friendly, slightly playful cooking personality.
                            - Return a recipe in this EXACT format:
                            
                              Title: ...
                              PrepTime: ...
                              CookTime: ...
                              Servings: ...
                              Ingredients: ...
                              Method: ...
                            
                            User request:
                            """ + userInput)
                    .model("gpt-4o-mini")
                    .build();

            Response response = client.responses().create(params);

            String text = response.output().stream()
                    .flatMap(item -> item.message().stream())          // Optional → stream
                    .flatMap(msg -> msg.content().stream())            // list
                    .flatMap(content -> content.outputText().stream())              // Optional → stream
                    .map(t -> t.text())                                // NOW this works
                    .findFirst()
                    .orElse("No response");

            chatbotOutput.setText(text);
            generatedRecipeText = text;

            System.out.println("AI says: " + text);
            return generatedRecipeText;
        } catch (Exception e) {
            e.printStackTrace();
            chatbotOutput.setText("Error: " + e.getMessage());
        }
        return null;
    }
    public String getRecipeText(){
        return generatedRecipeText;
    }
    public void saveRecipe(){
        if(chatbotOutput != null){
            saveRecipeButton.setText("Recipe Saved!");
            Recipe savedRecipe = new Recipe(generatedRecipeText);
            savedRecipesDAO.insertRecipe(savedRecipe);
        }
        else{
            saveRecipeButton.setText("Please generate a recipe first!");
        }

    }

    private void loadScene(String fxmlFile) throws IOException {
        Stage stage = (Stage) chatbotInput.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    public void initialize() {

        ScaleMainView();

    }

    public HBox AiRoot; //This Hbox is the main parent.


    private void ScaleMainView() {
        AiRoot.setScaleX(1.6); //Scales root parent by 1.6
        AiRoot.setScaleY(1.6);
    }

    @FXML
    public void goToGroceryList() throws IOException {
        loadScene("grocery-view.fxml");
    }

    @FXML
    public void goToAIPage() throws IOException {
        loadScene("ai-view.fxml");
    }

    @FXML
    public void goToPreferences() throws IOException {
        loadScene("preferences-view.fxml");
    }

    @FXML
    public void goToFitnessTargets() throws IOException {
        loadScene("fitness-targets-view.fxml");
    }

    @FXML
    public void goToSavedRecipeView() throws IOException {
        loadScene("saved-recipes-view.fxml");  }

    @FXML
    private String extract(String text, String key) {
        int start = text.indexOf(key);
        if (start == -1) return "";

        start += key.length();
        int end = text.indexOf("\n", start);

        return end == -1
                ? text.substring(start).trim()
                : text.substring(start, end).trim();
    }

    public void setChatbotInput(TextArea chatbotInput) {
        this.chatbotInput = chatbotInput;
    }

    public void setChatbotOutput(TextArea chatbotOutput) {
        this.chatbotOutput = chatbotOutput;
    }
}