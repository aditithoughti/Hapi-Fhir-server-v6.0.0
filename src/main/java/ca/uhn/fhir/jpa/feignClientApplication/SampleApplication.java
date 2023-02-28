package ca.uhn.fhir.jpa.feignClientApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import ca.uhn.fhir.jpa.starter.Application;

@SpringBootApplication
@EnableFeignClients
public class SampleApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(Application.class, args);
		
	}

}
