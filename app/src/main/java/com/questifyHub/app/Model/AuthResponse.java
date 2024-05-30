package com.questifyHub.app.Model;

public class AuthResponse {
    private boolean success;
    private String message;
    private String userLogged;
    private int companyId;
    public AuthResponse() {}
    public AuthResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    public AuthResponse(boolean success, String message, String userLogged, int companyId) {
        this.success = success;
        this.message = message;
        this.userLogged = userLogged;
        this.companyId = companyId;
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
