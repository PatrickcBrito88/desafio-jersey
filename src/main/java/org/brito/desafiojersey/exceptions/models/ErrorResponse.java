package org.brito.desafiojersey.exceptions.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ErrorResponse {
    private int status;
    private List<ValidationErrorDetail> erros;
    private String timestamp;

    public ErrorResponse(int status, List<ValidationErrorDetail> erros) {
        this.status = status;
        this.erros = erros;
        this.timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ValidationErrorDetail> getErros() {
        return erros;
    }

    public void setErros(List<ValidationErrorDetail> erros) {
        this.erros = erros;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
