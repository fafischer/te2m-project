package de.te2m.project.service.resources.exception;

import de.te2m.project.api.impl.model.ErrorResponse;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotImplementedExceptionHandler implements ExceptionMapper<NotImplementedException> {
	@Override
	public Response toResponse(NotImplementedException exception)
	{
		return Response.status(Status.NOT_IMPLEMENTED)
				.entity(new ErrorResponse().message(exception.getMessage()))
				.build();
	}
}
