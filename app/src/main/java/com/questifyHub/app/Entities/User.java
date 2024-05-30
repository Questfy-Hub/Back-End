package com.questifyHub.app.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.questifyHub.app.Exceptions.InvalidEmailException;

import java.nio.file.Files;
import java.util.List;
import com.questifyHub.app.Regex.Regex;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.File;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "role")
    private String role;
    @Column(name = "points")
    private int points;
    @Column
    private String gestor;

    private byte[] image;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_companyCode")
    private Company companyUser;

    @JsonBackReference
    @ManyToMany
    @JoinTable(name = "User_Set_Task", joinColumns = @JoinColumn(name = "fk_user"), inverseJoinColumns = @JoinColumn(name = "fk_task"))
    private List<Task> taskUser;

    public User() {
    }

    public User(Long id, String fullname, String username, String password, String email, String cpf, String role,
            int points, String gestor ,byte[] image,Company companyUser, List<Task> taskUser) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        try {
            if (Regex.validateEmail(email)) {
                this.email = email;
            }
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }
        this.cpf = cpf;
        this.role = role;
        this.points = points;
        this.image = image;
        this.gestor = gestor;
        this.companyUser = companyUser;
        this.taskUser = taskUser;
    }

    public User(String fullname, String username, String email, String cpf, String role, String password, Company company) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
        this.password = password;
        this.companyUser = company;
    }

    public User(String fullname, String username, String email, String cpf, String role, String password, byte[] bytes, Company companyUser) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
        this.password = password;
        this.image = bytes;
        this.companyUser = companyUser;
    }

    public Long getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

  public byte[] getImage() {return image;}

   public void setImage(byte[] image) {this.image = image;}

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompanyUser() {
        return companyUser;
    }

    public void setCompanyUser(Company companyUser) {
        this.companyUser = companyUser;
    }

    public List<Task> getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(List<Task> taskUser) {
        this.taskUser = taskUser;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", role='" + role + '\'' +
                ", role='" + role + '\'' +
                ", points=" + points +
                ", companyUser=" + companyUser +
                ", taskUser=" + taskUser +
                '}';
    }
}
