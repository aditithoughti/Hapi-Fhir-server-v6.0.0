package ca.uhn.fhir.jpa.starter;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.BaseServerResponseException;

@Service
@Interceptor

public class RequestExceptionInterceptor {

	@Hook(Pointcut.SERVER_HANDLE_EXCEPTION)
	public boolean handleException(
			RequestDetails theRequestDetails,
			BaseServerResponseException theException,
			HttpServletRequest theServletRequest,
			HttpServletResponse theServletResponse) throws IOException{
		
		theServletResponse.setStatus(theException.getStatusCode());
		theServletResponse.getWriter().append("Failed to process!");
		theServletResponse.getWriter().close();
		return false;
		
	}
	
}
