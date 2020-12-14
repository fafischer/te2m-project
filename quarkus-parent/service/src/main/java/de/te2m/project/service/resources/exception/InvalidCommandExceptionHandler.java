package de.te2m.project.service.resources.exception;

import de.te2m.project.api.impl.model.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidCommandExceptionHandler implements ExceptionMapper<InvalidCommandException> {
	@Override
	public Response toResponse(InvalidCommandException exception)
	{
		return Response.status(Status.BAD_REQUEST).entity(new ErrorResponse().message(exception.getMessage())).build();
	}
}
