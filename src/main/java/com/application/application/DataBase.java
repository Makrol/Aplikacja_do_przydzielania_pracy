package com.application.application;

import javafx.scene.control.Alert;

import java.sql.*;

public class DataBase {
    static String host="localhost";
    static String port="1521";
    static String name="xe";
    static String login="system";
    static String password="12345";

    static ResultSet executeQuery(String sql){
        try{
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@"+host+":"+port+":"+name,login,password);
            Statement stmt = con.createStatement();
            return stmt.executeQuery(sql);

        }catch (Exception e)
        {
            ErrorWindow.showWindow("Błąd bazy danych!",e.toString());
        }
        return null;
    }
    static User getUser(String login, String password) throws SQLException {
        ResultSet result = executeQuery("select * from users where login='"+login+"' and password='"+password+"'");
        if(result.next()){
            return new User(
                    result.getLong(1),
                    result.getLong(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5),
                    result.getString(6)
            );
        }
        return null;
    }
    static void addUser(String name,String surname,String login,String password){
        executeQuery("insert into users values(user_seq.nextval,0,'"+name+"','"+surname+"','"+login+"','"+password+"')");
    }


    static Long addTask(String title,String description){
        try{
                executeQuery("insert into tasks values(TASK_SEQ.nextval,'"+title+"','"+description+"')");
                executeQuery("update seq_num set max_task=max_task+1");
                ResultSet result = executeQuery("select * from seq_num");
                if(result.next()){
                    return result.getLong(1);
                }
        }catch(SQLException ex){
            ErrorWindow.showWindow("Błąd bazy danych1!",ex.toString());
        }
        ErrorWindow.showWindow("Błąd bazy danych!","Zadanie nie dodane.");
        return 0l;
    }

    static void addUserTask(Long user_id,Long task_id){
        executeQuery("insert into user_task values(user_task_seq.nextval,"+user_id+","+task_id+")");
        }


    static ResultSet getAllUsers(){
        return executeQuery("select * from users");
    }
    static User getUser(String login){
        ResultSet result = executeQuery("select * from users where login='"+login+"'");
        try{
            if(result.next()){
                return new User(
                        result.getLong(1),
                        result.getLong(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6)
                );
            }
        }catch (SQLException e){
            ErrorWindow.showWindow("Błąd bazy danych!",e.toString());
        }
        return null;
    }

    static ResultSet getAllUsersTasksTable(){
        return executeQuery("select t.title,t.description,u.name,u.surname,t.id\n" +
                                "from user_task ut,users u,tasks t\n" +
                                "where ut.user_id=u.id\n" +
                                "and t.id=ut.TASK_ID");
    }
    static ResultSet getUserTasksTable(Long userId){
        return executeQuery("select t.title,t.description,u.name,u.surname,t.id\n" +
                                "from user_task ut,users u,tasks t\n" +
                                "where ut.user_id=u.id\n" +
                                "and t.id=ut.TASK_ID\n" +
                                "and ut.user_id="+userId);
    }
    static void deleteTask(Long taskID){
        executeQuery("delete from user_task where task_id="+taskID);
        executeQuery("delete from tasks where id="+taskID);
    }
}