package com.questifyHub.app.Entities;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskCode;
    @Column(name = "shortDescription")
    private String shortDescription;
    @Column(name = "longDescription")
    private String longDescription;
    @Column(name  = "initialDate")
    private LocalDate initialDate;
    @Column(name = "conclusionDate")
    private LocalDate conclusionDate;
    @Column(name = "endLineDate")
    private LocalDate endLineDate;
    @Column(name = "dificulty")
    private int dificulty;

    public Task(){}

    public Task(int taskCode, String shortDescription, String longDescription, LocalDate initialDate,
            LocalDate conclusionDate, LocalDate endLineDate, int dificulty) {
        this.taskCode = taskCode;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.initialDate = initialDate;
        this.conclusionDate = conclusionDate;
        this.endLineDate = endLineDate;
        this.dificulty = dificulty;
    }

    public int getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(int taskCode) {
        this.taskCode = taskCode;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getConclusionDate() {
        return conclusionDate;
    }

    public void setConclusionDate(LocalDate conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public LocalDate getEndLineDate() {
        return endLineDate;
    }

    public void setEndLineDate(LocalDate endLineDate) {
        this.endLineDate = endLineDate;
    }

    public int getDificulty() {
        return dificulty;
    }

    public void setDificulty(int dificulty) {
        this.dificulty = dificulty;
    }
    
    
}
