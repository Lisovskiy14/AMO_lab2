package com.example.amo_lab2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);

        Controller controller = fxmlLoader.getController();
        controller.setPrimaryStage(stage);

        stage.setTitle("Лабораторна робота №2");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}