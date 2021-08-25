package com.todoproject.todo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name="TODO")
@Table(name = "TODO")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;


    @Column(name = "Task")
    private String task;
    @Column(name = "isdone")
    private boolean isDone;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "userid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "field")
    private User user;

    public TodoItem() {
    }

    public TodoItem(int id, String task, boolean isDone, User user) {
        this.id = id;
        this.task = task;
        this.isDone = isDone;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @JsonBackReference(value = "getter")
    public User getUsers() {
        return user;
    }

    public void setUsers(User user) {
        this.user = user;
    }
}
