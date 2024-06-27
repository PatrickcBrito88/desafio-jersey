package org.brito.desafiojersey.exceptions.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.brito.desafiojersey.exceptions.models.ErrorResponse;
import org.brito.desafiojersey.exceptions.models.SimpleErrorResponse;
import org.brito.desafiojersey.exceptions.models.ValidationErrorDetail;

import java.util.Collections;
import java.util.List;

public class ExceptionBuilder {

    private int status;
    private List<ValidationErrorDetail> message;
    private String simpleMessage;

    public ExceptionBuilder(int status, String field, String message) {
        this.status = status;
        this.message = Collections.singletonList(new ValidationErrorDetail(field, message));
    }

    public ExceptionBuilder(int status, List<ValidationErrorDetail> message) {
        this.status = status;
        this.message = message;
    }

    public ExceptionBuilder(int status, String message) {
        this.status = status;
        this.simpleMessage = message;
    }

    public Response build() {
        if (simpleMessage != null) {
            SimpleErrorResponse errorResponse = new SimpleErrorResponse(
                    status,
                    simpleMessage
            );
            return Response
                    .status(status)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } else {
            ErrorResponse errorResponse = new ErrorResponse(
                    status,
                    message
            );
            return Response
                    .status(status)
                    .entity(errorResponse)
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
