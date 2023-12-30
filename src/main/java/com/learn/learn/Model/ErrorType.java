package com.learn.learn.Model;

public class ErrorType {
    
    private String time;
    private String status;
    private String message;
    
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public ErrorType(String time, String status, String message) {
        this.time = time;
        this.status = status;
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
