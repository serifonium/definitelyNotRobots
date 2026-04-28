package com.example.definitelynotrobots;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static final String TITLE = "Not A Chef";

    @Override
    public void start(Stage stage) throws IOException {
        UserAccountDAO userAccountDAO = new UserAccountDAO();
        userAccountDAO.dropTable();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(TITLE);
        stage.setScene(scene);

        stage.setWidth(1920); //sets the width for every scene
        stage.setHeight(1080); //sets the height for every scene

        stage.setFullScreenExitHint(""); // removes annoying fullscreen message
        stage.show();
    }
}
