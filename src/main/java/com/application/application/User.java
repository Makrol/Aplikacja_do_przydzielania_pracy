package com.application.application;

public class User {
    private Long id;
    private Long admin;
    private String name;
    private String surname;
    private String login;
    private String password;

    public User(Long id, Long admin, String name, String surname, String login, String password) {
        this.id = id;
        this.admin = admin;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public Long getAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
