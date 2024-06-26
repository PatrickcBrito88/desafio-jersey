package org.brito.desafiojersey.controller;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

public interface DefaultController {

    default <T extends Object> Response retornarResponse(final Status httpStatus, final T response) {
        return Response.status(httpStatus).entity(new DefaultResponse<>(httpStatus.getStatusCode(), response)).build();
    }


    default <T extends Object> Response retornarSucesso(final T response) {
        return retornarResponse(Status.OK, response);
    }

}
