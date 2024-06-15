package com.questifyHub.app.Entities;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

/**
 * Classe Company onde é gerado a entidade Task (tarefas)
 * 
 * @author João Paulo Rezende de Oliveira
 */
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

    @Column(name = "initialDate")
    private LocalDate initialDate;

    @Column(name = "conclusionDate")
    private LocalDate conclusionDate;

    @Column(name = "endLineDate")
    private LocalDate endLineDate;

    @Column(name = "dificulty")
    private int dificulty;

    @Column(name = "priority")
    private String priority;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "fk_statusId")
    private Status statusTask;

    @JsonManagedReference
    @ManyToMany(mappedBy = "taskUser")
    private List<User> userTask;

    /**
     * Constructor Vazio
     * 
     */
    public Task() {
    }

    /**
     * Constructor da entidade Task
     * 
     * @param taskCode
     * @param shortDescription
     * @param longDescription
     * @param initialDate
     * @param conclusionDate
     * @param endLineDate
     * @param dificulty
     * @param priority
     * @param statusTask
     * @param userTask
     */
    public Task(int taskCode, String shortDescription, String longDescription, LocalDate initialDate,
            LocalDate conclusionDate, LocalDate endLineDate, int dificulty, String priority, Status statusTask,
            List<User> userTask) {
        this.taskCode = taskCode;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.initialDate = initialDate;
        this.conclusionDate = conclusionDate;
        this.endLineDate = endLineDate;
        this.dificulty = dificulty;
        this.priority = priority;
        this.statusTask = statusTask;
        this.userTask = userTask;
    }

    // Getters e Setters
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public List<User> getUserTask() {
        return userTask;
    }

    public void setUserTask(List<User> userTask) {
        this.userTask = userTask;
    }

    public Status getStatusTask() {
        return statusTask;
    }

    public void setStatusTask(Status statusTask) {
        this.statusTask = statusTask;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskCode=" + taskCode +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", initialDate=" + initialDate +
                ", conclusionDate=" + conclusionDate +
                ", endLineDate=" + endLineDate +
                ", dificulty=" + dificulty +
                ", priority='" + priority + '\'' +
                ", statusTask=" + statusTask +
                ", userTask=" + userTask +
                '}';
    }
}
