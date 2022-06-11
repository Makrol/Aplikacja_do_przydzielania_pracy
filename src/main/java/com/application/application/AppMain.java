package com.application.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppMain extends Application {

    static User currentUser;
    private static Stage mainStage;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        stage.setResizable(false);
        changeScreen("loginWindow.fxml",741.0f,277.0f);
        stage.setTitle("Aplikacja do przydziału zadań!");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    public static void changeScreen(String name, float width, float height) throws  IOException{
        mainStage.setScene(new Scene(new FXMLLoader(AppMain.class.getResource(name)).load(),width,height));
    }
}