package com.application.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminController {
    static Task currentSelectedTask;
    @FXML
    private Button addTaskButton;

    @FXML
    private ChoiceBox<String> chooseUser;

    @FXML
    private Label currency;

    @FXML
    private TextField description;

    @FXML
    private TextField loginRegister;

    @FXML
    private Button logoutButton;

    @FXML
    private Label money;

    @FXML
    private TextField name;

    @FXML
    private Text name_info;

    @FXML
    private TableView userTaskTable;

    private TableColumn<Task,String> titleColumn;
    private TableColumn<Task,String> nameColumn;
    private TableColumn<Task,String> surnameColumn;

    @FXML
    private PasswordField passwordRegister;

    @FXML
    private Button registerButton;

    @FXML
    private TextField subject;

    @FXML
    private TextField surname;

    @FXML
    private Text surname_info;

    @FXML
    private Text taskError;

    @FXML
    private Text userError;

    @FXML
    private Label wrongLogin;


    @FXML
    void initialize()throws Throwable{
        name_info.setText(AppMain.currentUser.getName());
        surname_info.setText(AppMain.currentUser.getSurname());
        initUsersChoiceBox();
        init_table();
        loadDataToContainer();
    }

    @FXML
    void addTask(ActionEvent event) {
        Long tmp = DataBase.addTask(subject.getText(),description.getText());
        DataBase.addUserTask(DataBase.getUser(chooseUser.getValue()).getId(),tmp);
        userTaskTable.getItems().add(new Task(
                subject.getText(),
                description.getText(),
                DataBase.getUser(chooseUser.getValue()).getName(),
                DataBase.getUser(chooseUser.getValue()).getSurname(),
                tmp
        ));
        subject.setText("");
        description.setText("");
    }

    @FXML
    void logout(ActionEvent event) throws Throwable{
        AppMain.changeScreen("loginWindow.fxml",741.0f,277.0f);
        AppMain.currentUser=null;
    }

    @FXML
    void userRegister(ActionEvent event) {
        DataBase.addUser(name.getText(),surname.getText(),loginRegister.getText(),passwordRegister.getText());
        chooseUser.getItems().add(loginRegister.getText());
        name.setText("");
        surname.setText("");
        loginRegister.setText("");
        passwordRegister.setText("");
    }

    private void initUsersChoiceBox(){
        ResultSet tmp = DataBase.getAllUsers();
        try {
            while (tmp.next()){
                chooseUser.getItems().add(tmp.getString(5));
            }
        }catch (SQLException exception){
            ErrorWindow.showWindow("Błąd bazy danych",exception.toString());
        }
    }
    private void init_table(){
        titleColumn = new TableColumn<>("Nazwa zadania");
        nameColumn = new TableColumn<>("Imie pracownika");
        surnameColumn = new TableColumn<>("Nazwisko pracownika");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        titleColumn.setPrefWidth(292);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setPrefWidth(330);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameColumn.setPrefWidth(330);

        userTaskTable.getColumns().add(titleColumn);
        userTaskTable.getColumns().add(nameColumn);
        userTaskTable.getColumns().add(surnameColumn);


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
    private void loadDataToContainer() throws Throwable{
        ResultSet result = DataBase.getAllUsersTasksTable();
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
    private void ShowTaskDetails(Task task){

        currentSelectedTask = task;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("showTasks.fxml"));

        Dialog dialog = new Dialog<>();

        try {
            dialog.setDialogPane(loader.load());
        }catch (IOException e){
            e.printStackTrace();
        }
        AdminDialogController.setDialog(dialog);
        dialog.showAndWait();
    }
}

