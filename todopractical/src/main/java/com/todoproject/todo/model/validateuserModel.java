package com.todoproject.todo.model;

public class validateuserModel {
    String id;
    String password;

    public validateuserModel() {
    }

    public validateuserModel(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
