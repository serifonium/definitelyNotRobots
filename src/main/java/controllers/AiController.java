package controllers;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AiController {

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
}