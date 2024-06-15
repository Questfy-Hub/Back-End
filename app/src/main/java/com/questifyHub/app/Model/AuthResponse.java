package com.questifyHub.app.Model;

/**
 * Classe AuthResponse onde é realizado a resposta de autenticação
 * 
 */
public class AuthResponse {
    private boolean success;
    private String message;
    private String userLogged;
    private int companyId;

    /**
     * Constructor vazio
     * 
     */
    public AuthResponse() {
    }

    /**
     * Constructor do AuthResponse
     * 
     * @param success
     * @param message
     */
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    /**
     * Constructor do AuthResponse
     * 
     * @param success
     * @param message
     * @param userLogged
     * @param companyId
     */
    public AuthResponse(boolean success, String message, String userLogged, int companyId) {
        this.success = success;
        this.message = message;
        this.userLogged = userLogged;
        this.companyId = companyId;
    }

    // Getters e Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserLogged() {
        return userLogged;
    }

    public void setUserLogged(String userLogged) {
        this.userLogged = userLogged;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "AuthResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", userLogged='" + userLogged + '\'' +
                '}';
    }
}
