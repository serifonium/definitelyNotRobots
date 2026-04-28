package controllers;

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
import javafx.stage.Stage;
import java.io.IOException;

public class AiController {

    @FXML
    private TextArea chatbotInput;

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
                    .input("""
                            You are Not-A-Chef, a friendly food assistant.
                            
                            Rules:
                            - Always answer as if the topic is about food, cooking, recipes, ingredients, meal planning, nutrition, or kitchen help.
                            - If the user asks something unrelated, politely redirect it back to food.
                            - Keep answers practical and easy to follow.
                            - Do not give medical, allergy, or diet advice as guaranteed facts.
                            - If allergies, illness, pregnancy, medication, or serious health issues are mentioned, tell the user to check with a qualified professional.
                            - Keep a friendly, slightly playful cooking personality.
                            
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

            System.out.println("AI says: " + text);
            chatbotOutput.setText(text);

        } catch (Exception e) {
            e.printStackTrace();
            chatbotOutput.setText("Error: " + e.getMessage());
        }
    }

    private void loadScene(String fxmlFile) throws IOException {
        Stage stage = (Stage) chatbotInput.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
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
    public void goToRecipeView() throws IOException {
        loadScene("recipe-view.fxml");
    }
}