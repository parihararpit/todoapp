package com.todoproject.todo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "usersDetails")
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;
    @Column(name = "firstname")
    private String firstname;
    @Column(name = "lastname")
    private String lastname;
    @Column(name="address")
    private String address;

    @Column(name="email",unique = true)
    private String email;
    @Column(name="password")
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "user")
    @JsonManagedReference(value = "field")
    private List<TodoItem> todoItems=new ArrayList<>();

    public User() {
    }

    public User(int id, String firstname, String lastname, String address, String email, String password, List<TodoItem> todoItems) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.todoItems = todoItems;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonManagedReference(value = "getter")
    public List<TodoItem> getTodoItems() {
        return todoItems;
    }


    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
