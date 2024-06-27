package org.brito.desafiojersey.exceptions.mapper;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.brito.desafiojersey.exceptions.models.ValidationErrorDetail;

import java.util.List;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {


    @Override
    public Response toResponse(ConstraintViolationException exception) {

        List<ValidationErrorDetail> errors = exception.getConstraintViolations().stream()
                .map(v -> new ValidationErrorDetail(
                        getFieldName(v),
                        v.getMessage()))
                .collect(Collectors.toList());

        return new ExceptionBuilder(
                Response.Status.BAD_REQUEST.getStatusCode(),
                errors
        ).build();
    }

    private String getFieldName(ConstraintViolation<?> v) {
        String path = v.getPropertyPath().toString();
        return path.substring(path.lastIndexOf('.') + 1);
    }
}
