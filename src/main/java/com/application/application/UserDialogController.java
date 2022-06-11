package com.application.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;

public class UserDialogController {
    static private Dialog taskDetails;
    static UserController parent;

    @FXML
    private Label descriptionShowAdmin;

    @FXML
    private Button finishTaskButton;

    @FXML
    private Label subjectShowAdmin;

    @FXML
    void finishTask(ActionEvent event) {
        closeDialog(new ActionEvent());
        DataBase.deleteTask(UserController.currentSelectedTask.getTaskId());

        for(Object t :parent.userTaskTable.getItems()){
            if(UserController.currentSelectedTask.getTaskId()==((Task)t).getTaskId()){
                parent.userTaskTable.getItems().remove(t);
                return;
            }
        }

    }

    @FXML
    void initialize() {
        descriptionShowAdmin.setText(UserController.currentSelectedTask.getDescription());
        subjectShowAdmin.setText(UserController.currentSelectedTask.getTitle());
    }

    @FXML
    void closeDialog(ActionEvent event) {
        taskDetails.setResult(true);
        taskDetails.close();
    }

    static public void setDialog(Dialog dialog) {
        taskDetails = dialog;
    }
}
