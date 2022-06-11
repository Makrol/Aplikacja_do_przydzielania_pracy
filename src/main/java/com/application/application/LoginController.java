package com.application.application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController{
    @FXML
    private TextField db_login;

    @FXML
    private TextField db_name;

    @FXML
    private TextField db_password;

    @FXML
    private TextField host;

    @FXML
    private TextField port;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField login;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordLogin;

    @FXML
    private Label wrongLogin;

    @FXML
    void cancelLogin(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void userLogin(ActionEvent event) throws Throwable {
        AppMain.currentUser = DataBase.getUser(login.getText(),passwordLogin.getText());
        if(AppMain.currentUser==null)
            wrongLogin.setText("Niepoprawne dane logowania!!!");
        else if(AppMain.currentUser.getAdmin()==1)
            AppMain.changeScreen("adminPanel.fxml",952.0f,614.0f);
        else
            AppMain.changeScreen("userPanel.fxml",952.0f,614.0f);
    }
    @FXML
    void saveDataBaseSettings(ActionEvent event) {
        DataBase.host = host.getText();
        DataBase.port = port.getText();
        DataBase.name = db_name.getText();
        DataBase.login = db_login.getText();
        DataBase.password = db_password.getText();

        host.setPromptText(host.getText());
        port.setPromptText(port.getText());
        db_name.setPromptText(db_name.getText());
        db_login.setPromptText(db_login.getText());
        db_password.setPromptText(db_password.getText());

        host.clear();
        port.clear();
        db_name.clear();
        db_login.clear();
        db_password.clear();
    }
}