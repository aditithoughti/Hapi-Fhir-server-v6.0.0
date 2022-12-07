package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.springframework.stereotype.Service;

@Service
@Interceptor
public class TagTrimmingInterceptors {

	/** Handle creates */
	@Hook(Pointcut.STORAGE_PRESTORAGE_RESOURCE_CREATED)
	public void insert(IBaseResource theResource) {
		theResource.getMeta().getTag().clear();
		theResource.getMeta().getProfile().clear();
		theResource.getMeta().getSecurity().clear();

		System.out.println("Storage prestorage resource created");
	}

	/** Handle updates */
	@Hook(Pointcut.STORAGE_PRESTORAGE_RESOURCE_UPDATED)
	public void update(IBaseResource theOldResource, IBaseResource theResource) {
		theResource.getMeta().getTag().clear();
		theResource.getMeta().getProfile().clear();
		theResource.getMeta().getSecurity().clear();

		System.out.println("Storage prestorage resource updated");
	}

}
