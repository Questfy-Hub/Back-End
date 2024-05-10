package com.questifyHub.app.Entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="status")
public class Status {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int statusCode;
    @Column(name = "description")
    private String description;
    @Column(name ="statusName")
    private String statusName;

    @OneToMany(mappedBy = "statusTask")
    private List<Task> taskList;


    public Status(){}
    public Status(int statusCode, String description, String statusName, List<Task> taskList) {
        this.statusCode = statusCode;
        this.description = description;
        this.statusName = statusName;
        this.taskList = taskList;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
  
}