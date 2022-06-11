package com.application.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

public class AdminDialogController {
    static private Dialog taskDetails;

    @FXML
    private Label descriptionShowAdmin;

    @FXML
    private Label subjectShowAdmin;

    @FXML
    void initialize(){
        descriptionShowAdmin.setText(AdminController.currentSelectedTask.getDescription());
        subjectShowAdmin.setText(AdminController.currentSelectedTask.getTitle());
    }

    @FXML
    void closeDialog(ActionEvent event) {
        taskDetails.setResult(true);
        taskDetails.close();
    }
    static public void setDialog(Dialog dialog){
        taskDetails=dialog;
    }
}
