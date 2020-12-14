package de.aclue.demowebresttemplateclient.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Jonas Keßler (jonas.kessler@aclue.de)
 */
@Configuration
public class AppConfig {

	@Bean
	RestTemplate scoreAgencyRestTemplate(RestTemplateBuilder builder) {
		return builder
				.rootUri("http://localhost:8090")
				.setConnectTimeout(Duration.ofSeconds(10L))
				.setReadTimeout(Duration.ofSeconds(15L))
				.build();
	}
}
