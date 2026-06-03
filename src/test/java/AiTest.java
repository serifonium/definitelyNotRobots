import controllers.AiController;
import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AiTest {
    @BeforeAll
    static void initJavaFX() {
        Platform.startup(() -> {});
    }

    @Test
    void emptyInputShowsMessage() {
        AiController aiController = new AiController();

        TextArea input = new TextArea();
        TextArea output = new TextArea();

        input.setText("");

        aiController.setChatbotInput(input);
        aiController.setChatbotOutput(output);

        aiController.onChatbotInputButtonClick();

        assertEquals("Type something first.", output.getText());
    }
}