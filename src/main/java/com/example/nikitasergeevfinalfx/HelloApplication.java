package com.example.nikitasergeevfinalfx;

import com.example.nikitasergeevfinalfx.jdbc.AppConfig;
import com.example.nikitasergeevfinalfx.jdbc.DatabaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        AppConfig config = new AppConfig();
        new DatabaseInitializer(config).init();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 850, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
