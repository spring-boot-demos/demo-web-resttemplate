package de.aclue.demowebresttemplateclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoWebResttemplateClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebResttemplateClientApplication.class, args);
	}

}
