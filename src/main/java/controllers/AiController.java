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

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import com.openai.models.responses.ResponseInputImage;
import com.openai.models.responses.ResponseInputItem;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import java.io.File;
import java.io.IOException;
import javafx.scene.Parent;
import com.example.definitelynotrobots.Recipe;
import com.example.definitelynotrobots.RecipeParser;
import com.example.definitelynotrobots.UserAccountDAO;

public class AiController {
    @FXML
    private TextArea chatbotInput;

    @FXML
    private TextArea chatbotOutput;

    public String generatedRecipeText;

    private final SavedRecipesDAO savedRecipesDAO = new SavedRecipesDAO();
    private final OpenAIClient client = OpenAIOkHttpClient.fromEnv();
    @FXML
    private ImageView uploadedImagePreview;
    private File selectedImageFile;

    @FXML
    public void onChatbotInputButtonClick() {
        String userInput = chatbotInput.getText();

        if ((userInput == null || userInput.isEmpty()) && selectedImageFile == null) {
            chatbotOutput.setText("Type something or upload an image first.");
            return;
        }

        try {
            String prompt = """
                You are Not-A-Chef, a friendly food assistant.

                Rules:
                - Always answer as if the topic is about food, cooking, recipes, ingredients, meal planning, nutrition, or kitchen help.
                - If the user asks something unrelated, politely redirect it back to food.
                - If an image is provided, analyse it as a food/cooking image.
                - If the image contains food, identify likely ingredients or meal ideas.
                - If the image does not contain food, politely redirect back to food.
                - Keep answers practical and easy to follow.
                - Do not give medical, allergy, or diet advice as guaranteed facts.
                - If allergies, illness, pregnancy, medication, or serious health issues are mentioned, tell the user to check with a qualified professional.
                - Keep a friendly, slightly playful cooking personality.
                - Return a recipe in this EXACT format when making a recipe:

                  Title: ...
                  PrepTime: ...
                  CookTime: ...
                  Servings: ...
                  Ingredients: ...
                  Method: ...

                User request:
                """ + userInput;

            List<ResponseInputItem> inputItems = new ArrayList<>();

            ResponseInputItem.Message.Builder messageBuilder =
                    ResponseInputItem.Message.builder()
                            .role(ResponseInputItem.Message.Role.USER)
                            .addInputTextContent(prompt);

            if (selectedImageFile != null) {
                String imageDataUrl = imageFileToDataUrl(selectedImageFile);

                messageBuilder.addContent(
                        ResponseInputImage.builder()
                                .imageUrl(imageDataUrl)
                                .detail(ResponseInputImage.Detail.AUTO)
                                .build()
                );
            }

            inputItems.add(ResponseInputItem.ofMessage(messageBuilder.build()));

            ResponseCreateParams params = ResponseCreateParams.builder()
                    .inputOfResponse(inputItems)
                    .model("gpt-4.1-mini-2025-04-14")
                    .build();

            Response response = client.responses().create(params);

            String text = response.output().stream()
                    .flatMap(item -> item.message().stream())
                    .flatMap(msg -> msg.content().stream())
                    .flatMap(content -> content.outputText().stream())
                    .map(t -> t.text())
                    .findFirst()
                    .orElse("No response");

            chatbotOutput.setText(text);
            generatedRecipeText = text;

            System.out.println("AI says: " + text);

        } catch (Exception e) {
            e.printStackTrace();
            chatbotOutput.setText("Error: " + e.getMessage());
        }
    }

    @FXML
    public void onUploadImageClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");

        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Image Files",
                        "*.png", "*.jpg", "*.jpeg", "*.gif", "*.bmp"
                )
        );

        File file = fileChooser.showOpenDialog(stage);

        if (file == null) {
            return;
        }

        selectedImageFile = file;

        Image image = new Image(file.toURI().toString());
        uploadedImagePreview.setImage(image);

        chatbotOutput.setText("Image uploaded: " + file.getName());
    }

    private String imageFileToDataUrl(File imageFile) throws IOException {
        String mimeType = Files.probeContentType(imageFile.toPath());

        if (mimeType == null) {
            mimeType = "image/jpeg";
        }

        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        return "data:" + mimeType + ";base64," + base64Image;
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

    private void loadScene(String fxmlFile) throws IOException {
        Stage stage = (Stage) chatbotInput.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlFile));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    public void initialize() {
        ScaleMainView(1.65);
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



    public void goToFitnessTargets() throws IOException {
        loadScene("fitness-targets-view.fxml");
    }

    public void goToSavedRecipeView() throws IOException {
        loadScene("saved-recipes-view.fxml");  }





    public void setChatbotInput(TextArea chatbotInput) {
        this.chatbotInput = chatbotInput;
    }

    public void setChatbotOutput(TextArea chatbotOutput) {
        this.chatbotOutput = chatbotOutput;
    }
}
