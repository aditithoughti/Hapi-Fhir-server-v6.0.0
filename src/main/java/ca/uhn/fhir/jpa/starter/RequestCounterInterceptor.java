package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Interceptor
public class RequestCounterInterceptor {

	private int myRequestCount;

	public int getRequestCount() {
		return myRequestCount;
	}

	/**
	 * Override the incomingRequestPreProcessed method, which is called
	 * for each incoming request before any processing is done
	 */
	@Hook(Pointcut.SERVER_INCOMING_REQUEST_PRE_PROCESSED)
	public boolean incomingRequestPreProcessed(HttpServletRequest theRequest, HttpServletResponse theResponse) {
		myRequestCount++;
		System.out.println("Count of the requests : " + myRequestCount);
		return true;
	}

}
