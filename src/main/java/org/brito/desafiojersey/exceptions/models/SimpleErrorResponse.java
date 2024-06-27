package org.brito.desafiojersey.exceptions.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleErrorResponse {
    private int status;
    private String message;
    private String timestamp;

    public SimpleErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    // Getters e setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
