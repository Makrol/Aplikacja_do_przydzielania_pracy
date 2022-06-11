package com.application.application;

import javafx.scene.control.Alert;

public class ErrorWindow {
    static void showWindow(String title,String info){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(info);
        alert.showAndWait();
        System.exit(1);
    }
}
