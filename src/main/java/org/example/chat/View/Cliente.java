package org.example.chat.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.View;
import java.io.IOException;

public class Cliente extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/chat/Cliente.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 530);
        stage.setTitle("Chat");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}