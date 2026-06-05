//package java;

import com.example.definitelynotrobots.UserAccount;
import com.example.definitelynotrobots.UserAccountDAO;
import controllers.MainController;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javafx.application.Platform;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest {
//    @BeforeAll
//    static void initJavaFX() {
//        Platform.startup(() -> {});
//    }
//
//    @BeforeEach
//    public void setUp() {
//        UserAccountDAO.currentAccount = new UserAccount("TestUser", "123", "User", "Test");
//    }
//
//    @Test
//    void testSetNameUpdatesWelcomeMessage() {
//        MainController controller = new MainController();
//
//        controller.testLabel = new Label();
//
//        controller.SetName();
//
//        assertEquals("Welcome, User!", controller.testLabel.getText());
//    }
//
//    @Test
//    void testSavedMealsCount() {
//        VBox savedMeals = new VBox();
//
//        for (int i = 0; i < 8; i++) {
//            savedMeals.getChildren().add(new VBox());
//        }
//
//        assertEquals(8, savedMeals.getChildren().size());
//    }
//
//    @Test
//    void testSetSavedMealsClearsPreviousMeals() throws IOException {
//        MainController controller = new MainController();
//
//        controller.SavedMeals = new VBox();
//
//        controller.SetSavedMeals();
//        controller.SetSavedMeals();
//
//        assertEquals(8, controller.SavedMeals.getChildren().size());
//    }
}