package org.java.javaCoder.simpleMessenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException e) {
		ErrorMessage errorMessage=new ErrorMessage(e.getMessage(),404,"https://www.google.com/");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}
	

}
