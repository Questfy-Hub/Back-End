package com.questifyHub.app.Entities;

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

    @ManyToOne
    @JoinColumn(name = "fk_taskId")
    private Task taskStatus;


    public Status(){}



    public Status(int statusCode, String description, String statusName, Task taskStatus) {
        this.statusCode = statusCode;
        this.description = description;
        this.statusName = statusName;
        this.taskStatus = taskStatus;
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

    public Task getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Task taskStatus) {
        this.taskStatus = taskStatus;
    }

    
    
    
}
