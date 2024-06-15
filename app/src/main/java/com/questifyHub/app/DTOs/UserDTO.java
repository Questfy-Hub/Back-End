package com.questifyHub.app.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * classe UserDTO para transporte dos dados do objeto User para melhor
 * visualização
 * 
 */
public class UserDTO {
    private Long id;
    private String fullname;
    private String username;
    private String password;
    private String email;
    private String cpf;
    private String role;
    private int points;
    private byte[] image;

    /**
     * Constructor Vazio
     * 
     */
    public UserDTO() {
    }

    /**
     * Contructor do DTO de usuários
     * 
     * @param username
     * @param email
     * @param role
     * @param points
     */
    public UserDTO(String username, String email, String role, int points) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.points = points;
    }

    /**
     * Constructor do DTO de usuários
     * 
     * @param fullname
     * @param username
     * @param points
     * @param email
     * @param role
     */
    public UserDTO(String fullname, String username, int points, String email, String role) {
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.points = points;
        this.role = role;
    }

    /**
     * Constructor do DTO de usuários
     * 
     * @param id
     * @param fullname
     * @param username
     * @param password
     * @param email
     * @param cpf
     * @param role
     * @param points
     * @param image
     */
    public UserDTO(Long id, String fullname, String username, String password, String email, String cpf, String role,
            int points, byte[] image) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cpf = cpf;
        this.role = role;
        this.points = points;
        this.image = image;

    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
