package ca.uhn.fhir.jpa.starter;

import java.util.List;

import ca.uhn.fhir.rest.server.interceptor.auth.*;
import com.github.jsonldjava.shaded.com.google.common.collect.Lists;
import org.hl7.fhir.dstu3.model.IdType;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.stereotype.Service;

import ca.uhn.fhir.i18n.Msg;
import ca.uhn.fhir.interceptor.api.HookParams;
import ca.uhn.fhir.interceptor.api.IInterceptorBroadcaster;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.annotation.ConditionalUrlParam;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Update;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.api.server.storage.TransactionDetails;
import ca.uhn.fhir.rest.server.exceptions.AuthenticationException;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;

@Service
@Interceptor
@SuppressWarnings("ConstantConditions")
public class PatientAndAdminAuthorizationInterceptor extends AuthorizationInterceptor {

	@Override
	public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {

		String authHeader = theRequestDetails.getHeader("Authorization");
		String resourceType = theRequestDetails.getResourceName();

		if ("Bearer dfw98h38r".equals(authHeader) && resourceType.equals("Observation")) {

			return new RuleBuilder().allow().read().resourcesOfType("Observation").withAnyId().andThen().build();
		} 
		else if ("Bearer 39ff939jgg".equals(authHeader) && resourceType.equals("Observation")) {

			return new RuleBuilder().allow().read().resourcesOfType("Observation").withAnyId().andThen().allow().write()
					.resourcesOfType("Observation").withAnyId().andThen().build();
		} 
		else {
			return new RuleBuilder().denyAll("Wrong user or Wrong resource!").build();
		}

	}

}
