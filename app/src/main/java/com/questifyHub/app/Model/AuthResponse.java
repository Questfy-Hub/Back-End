package com.questifyHub.app.Model;

public class AuthResponse {
    private boolean success;
    private String message;
    private String userLogged;
    public AuthResponse() {}
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public AuthResponse(boolean success, String message, String userLogged) {
        this.success = success;
        this.message = message;
        this.userLogged = userLogged;
    }

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
    @Override
    public String toString() {
        return "AuthResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", userLogged='" + userLogged + '\'' +
                '}';
    }
}
