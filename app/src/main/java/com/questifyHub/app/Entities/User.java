package com.questifyHub.app.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.questifyHub.app.Exceptions.InvalidEmailException;
import java.util.List;
import com.questifyHub.app.Regex.Regex;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

/**
 * Classe Company onde é gerado a entidade Users
 * 
 * @author João Paulo Rezende de Oliveira
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    /**
     * Constructor Vazio
     * 
     */
    private byte[] image;


    @ManyToOne
    @JoinColumn(name = "fk_companyCode")
    private Company companyUser;


    @ManyToMany
    @JoinTable(name = "User_Set_Task", joinColumns = @JoinColumn(name = "fk_user"), inverseJoinColumns = @JoinColumn(name = "fk_task"))
    private List<Task> taskUser;

    /**
     * Constructor vazio
     * 
     */
    public User() {
    }

    /**
     * Constructor da entidade User
     * 
     * @param id
     * @param fullname
     * @param username
     * @param password
     * @param email
     * @param cpf
     * @param role
     * @param points
     * @param gestor
     * @param image
     * @param companyUser
     * @param taskUser
     */
    public User(Long id, String fullname, String username, String password, String email, String cpf, String role,
            int points, String gestor, byte[] image, Company companyUser, List<Task> taskUser) {
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

    /**
     * Constructor da entidade User
     * 
     * @param fullname
     * @param username
     * @param email
     * @param cpf
     * @param role
     * @param password
     * @param company
     */
    public User(String fullname, String username, String email, String cpf, String role, String password,
            Company company) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
        this.password = password;
        this.companyUser = company;
    }

    /**
     * Constructor da entidade User
     * 
     * @param fullname
     * @param username
     * @param email
     * @param cpf
     * @param role
     * @param password
     * @param bytes
     * @param companyUser
     */
    public User(String fullname, String username, String email, String cpf, String role, String password, byte[] bytes,
            Company companyUser) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
        this.password = password;
        this.image = bytes;
        this.companyUser = companyUser;
    }

    // Getters e Setters
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

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
