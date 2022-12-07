package ca.uhn.fhir.jpa.starter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import ca.uhn.fhir.jpa.rp.r5.PatientResourceProvider;
import ca.uhn.fhir.jpa.starter.PatientAndAdminAuthorizationInterceptor;
import ca.uhn.fhir.jpa.starter.resourceprovider.MyPatientResourceProvider;
import ca.uhn.fhir.rest.server.IResourceProvider;

@Import(AppProperties.class)
public class JpaRestfulServer extends BaseJpaRestfulServer {

	@Autowired
	AppProperties appProperties;

	// @Autowired
//	RequestCounterInterceptor requestCounterInterceptor;
//	@Autowired
//	SimpleServerLoggingInterceptor simpleServerLoggingInterceptor;
//	@Autowired
//	TagTrimmingInterceptors tagTrimmingInterceptors;
//	@Autowired
//	RequestExceptionInterceptor requestExceptionInterceptor;

//	@Autowired
//	PatientAndAdminAuthorizationInterceptor patientAndAdminAuthorizationInterceptor;

	@Autowired
	BasicSecurityInterceptor basicSecurityInterceptor;

	private static final long serialVersionUID = 1L;

	public JpaRestfulServer() {
		super();
	}

	@Override
	protected void initialize() throws ServletException {
		super.initialize();

//		registerInterceptor(requestCounterInterceptor);
//		registerInterceptor(simpleServerLoggingInterceptor);
//		registerInterceptor(tagTrimmingInterceptors);
//		registerInterceptor(requestExceptionInterceptor);

//		registerInterceptor(patientAndAdminAuthorizationInterceptor);

		registerInterceptor(basicSecurityInterceptor);

		List<IResourceProvider> resourceProviders = new ArrayList<IResourceProvider>();
		resourceProviders.add(new MyPatientResourceProvider());
		registerProviders(resourceProviders);
	}
}
