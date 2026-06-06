package com.example.definitelynotrobots;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controls user input to a GPT-4o model.
 */
public class AiController {

    /**
     * The text area for a user to input recipe requests.
     */
    @FXML
    private TextField chatbotInput;

    /**
     * The text area for the GPT-4o model to output recipe details.
     */
    @FXML
    private TextArea chatbotOutput;

    /**
     * Connection to a GPT-4o model.
     */
    private final OpenAIClient client = OpenAIOkHttpClient.fromEnv();

    /**
     * Submit user text to a GPT-4o model.
     */
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
}