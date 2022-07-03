package no.sonat.assignment.boundary;

import no.sonat.assignment.exception.CouldNotRecycleException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CouldNotRecycleExceptionMapper implements ExceptionMapper<CouldNotRecycleException> {

    @Override
    public Response toResponse(CouldNotRecycleException exception) {
        return Response.serverError().entity(exception.getMessage()).build();
    }
}
