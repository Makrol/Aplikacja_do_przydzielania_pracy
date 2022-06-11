package com.application.application;

public class Task {
    private Long taskId;
    private String title;
    private String description;
    private String name;
    private String surname;

    public Task(String title, String description, String name, String surname,Long taskId) {
        this.title = title;
        this.description = description;
        this.name = name;
        this.surname = surname;
        this.taskId = taskId;
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
