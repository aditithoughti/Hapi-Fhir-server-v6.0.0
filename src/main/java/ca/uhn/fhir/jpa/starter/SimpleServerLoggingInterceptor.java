package ca.uhn.fhir.jpa.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.rest.api.server.RequestDetails;

@Service
@Interceptor
public class SimpleServerLoggingInterceptor {

	private final Logger ourLog = LoggerFactory.getLogger(SimpleServerLoggingInterceptor.class);

	@Hook(Pointcut.SERVER_INCOMING_REQUEST_PRE_HANDLED)
	public void logRequests(RequestDetails theRequest) {
		ourLog.info("Request of type {} with request ID: {}", theRequest.getOperation(), theRequest.getRequestId());
	
	
	}
	

}


