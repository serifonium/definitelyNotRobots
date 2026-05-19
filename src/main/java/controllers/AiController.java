package controllers;

import com.example.definitelynotrobots.*;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    private TextArea chatbotInput;

    @FXML
    private TextArea chatbotOutput;
    @FXML
    public String generatedRecipeText;

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
                            - Return a recipe in this EXACT format with no asterisks:
                            
                              Title: ...
                              Prep Time: ...
                              Cook Time: ...
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

    private void loadScene(String fxmlFile) throws IOException {
        Stage stage = (Stage) chatbotInput.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    public void initialize() {
        ScaleMainView(1.65);
    }
    @FXML
    private void openRecipeView() {
        String aiText = chatbotOutput.getText();

        Recipe recipe = RecipeParser.parse(aiText, UserAccountDAO.currentAccount.getID());

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/definitelynotrobots/recipe-view.fxml"));
            Parent root = loader.load();

            RecipeController controller = loader.getController();
            controller.setRecipe(recipe);

            Stage stage = (Stage) chatbotOutput.getScene().getWindow();
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public HBox AiRoot; //This Hbox is the main parent.


    private void ScaleMainView(double scale) {
        AiRoot.setScaleX(scale); //Scales root parent
        AiRoot.setScaleY(scale);
    }

    @FXML
    public void goToGroceryList() throws IOException {
        loadScene("grocery-view.fxml");
    }

    public void goToAIPage() throws IOException {
        loadScene("ai-view.fxml");
    }

    public void goToHomeView() throws IOException {
        loadScene("main-view.fxml");
    }
    public void goToProfile() throws IOException {
        loadScene("Profile-view.fxml");
    }

    public void goToPreferences() throws IOException {
        loadScene("preferences-view.fxml");
    }

    public void goToFitnessTargets() throws IOException {
        loadScene("fitness-targets-view.fxml");
    }

    public void goToSavedRecipeView() throws IOException {
        loadScene("saved-recipes-view.fxml");  }

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