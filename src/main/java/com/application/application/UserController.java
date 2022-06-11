package com.application.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;

public class UserController {
    static Task currentSelectedTask;
    @FXML
    private Label currency;

    @FXML
    private Button logoutButton;

    @FXML
    private Label money;

    @FXML
    private Text name_info;

    @FXML
    public TableView userTaskTable;

    private TableColumn<Task,String> titleColumn;

    @FXML
    private Text surname_info;

    @FXML
    void initialize() throws Throwable{
        name_info.setText(AppMain.currentUser.getName());
        surname_info.setText(AppMain.currentUser.getSurname());
        init_table();
        loadDataToContainer();
        UserDialogController.parent=this;
    }

    @FXML
    void logout(ActionEvent event) throws Throwable {
        AppMain.changeScreen("loginWindow.fxml",741.0f,277.0f);
        AppMain.currentUser=null;
    }
    private void init_table(){
        titleColumn = new TableColumn<>("Nazwa zadania");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(952);

        userTaskTable.getColumns().add(titleColumn);

        userTaskTable.setRowFactory(tv->{
            TableRow<Task> row = new TableRow<>();
            row.setOnMouseClicked(event->{
                if(event.getClickCount()==2 && (!row.isEmpty())){
                    ShowTaskDetails(row.getItem());
                }
            });
            return row;
        });


    }
    private void ShowTaskDetails(Task task){
        currentSelectedTask = task;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("showTask.fxml"));

        Dialog dialog = new Dialog<>();

        try {
            dialog.setDialogPane(loader.load());
        }catch (IOException e){
            e.printStackTrace();
        }
        UserDialogController.setDialog(dialog);
        dialog.showAndWait();
    }
    private void loadDataToContainer() throws Throwable{
        ResultSet result = DataBase.getUserTasksTable(AppMain.currentUser.getId());
        while(result.next()){
            userTaskTable.getItems().add(new Task(
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getLong(5)
            ));
        }
    }
}

