package com.example.definitelynotrobots;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * Main JavaFX application to use.
 */
public class HelloApplication extends Application {
    /**
     * Title of the window.
     */
    public static final String TITLE = "Not A Chef";
    /**
     * Width of the window in pixels.
     */
    public static final Integer WIDTH = 1920;
    /**
     * Height of the window in pixels.
     */
    public static final Integer HEIGHT = 1080;

    @Override
    public void start(Stage stage) throws IOException {
        UserAccountDAO userAccountDAO = new UserAccountDAO();
//       userAccountDAO.dropTable();

        FitnessDAO fitnessDAO = new FitnessDAO();
        /* fitnessDAO.dropTable(); */


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(TITLE);
        stage.setScene(scene);

        stage.setWidth(WIDTH); //sets the width for every scene
        stage.setHeight(HEIGHT); //sets the height for every scene

        stage.setMaximized(true); //maximizes the app window
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("images/Textlesslogo.png")))); //sets the app's icon
        stage.show();
    }
}
