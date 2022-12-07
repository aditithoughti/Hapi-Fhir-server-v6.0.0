package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.i18n.Msg;
import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import ca.uhn.fhir.jpa.starter.model.dao.UserLogRepository;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.interceptor.auth.AuthorizationInterceptor;
import ca.uhn.fhir.rest.server.interceptor.auth.IAuthRule;
import ca.uhn.fhir.rest.server.interceptor.auth.PolicyEnum;
import ca.uhn.fhir.rest.server.interceptor.auth.RuleBuilder;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.github.jsonldjava.shaded.com.google.common.collect.Lists;

import java.util.List;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Interceptor
public class BasicSecurityInterceptor {
//	@Autowired
	UserLogRepository userLogRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;


	/**
	 * This intercepter implements HTTP Basic Auth, which specifies that a username
	 * and password are provided in a header called Authorization.
	 */
	@Hook(Pointcut.SERVER_INCOMING_REQUEST_POST_PROCESSED)
	public boolean incomingRequestPostProcessed(RequestDetails theRequestDetails, HttpServletRequest theRequest,
															  HttpServletResponse theResponse) throws AuthenticationException {
		String authHeader = theRequest.getHeader("Authorization");

		// The format of the header must be:
		// Authorization: Basic [base64 of username:password]
//		if (authHeader == null || !authHeader.startsWith("Basic ")) {
//			throw new AuthenticationException(Msg.code(642) + "Missing or invalid Authorization header");
//		}
//
//		String base64 = authHeader.substring("Basic ".length());
//		String base64decoded = new String(Base64.decodeBase64(base64));
//		String[] parts = base64decoded.split(":");
//
//		String username = parts[0];
//		String password = parts[1];
//
//		jdbcTemplate.execute(String.format("select * from user_log (username, password) " +
//			"values('%s', '%s')"));
		
		new AuthorizationInterceptor(PolicyEnum.DENY) {
			   @Override
			   public List<IAuthRule> buildRuleList(RequestDetails theRequestDetails) {
			      return new RuleBuilder()
			         .allow().bulkExport().systemExport().withResourceTypes(Lists.newArrayList("Patient", "Encounter", "Observation"))
			         .build();
			   }
			};

		/*
		 * Here we test for a hardcoded username & password. This is not typically how
		 * you would implement this in a production system of course.
		 */
//		if (!username.equals("someuser") || !password.equals("thepassword")) {
//			throw new AuthenticationException(Msg.code(643) + "Invalid username or password");
//		}


		// Return true to allow the request to proceed
		return true;
	}

}
