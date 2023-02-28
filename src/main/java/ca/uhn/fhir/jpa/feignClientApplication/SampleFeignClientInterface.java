package ca.uhn.fhir.jpa.feignClientApplication;

import java.util.List;

import javax.mail.Store;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="stores")
public interface SampleFeignClientInterface {

	@RequestMapping(method = RequestMethod.GET, value = "/stores")
	List<Store> getStores();
	
	@RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") Long storeId, Store store);
	
}