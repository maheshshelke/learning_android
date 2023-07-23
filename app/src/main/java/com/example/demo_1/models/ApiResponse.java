package com.example.demo_1.models;

public class ApiResponse {
    private boolean status;
    private int statusCode;
    private String message;
    private String data;

    public ApiResponse() {
    }

    public ApiResponse(boolean status, int statusCode, String message, String data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "status=" + status +
                ", statusCode=" + statusCode +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
