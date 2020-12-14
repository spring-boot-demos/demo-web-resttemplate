package de.aclue.demowebresttemplateclient.service;

import javax.annotation.PostConstruct;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Jonas Keßler (jonas.kessler@aclue.de)
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CreditAgencyClient {

	private final RestTemplate restTemplate;

	@PostConstruct
	public void postConstruct() {
		
		/*
		 * Aufgabe 1
		 */
		String stringResponse = restTemplate.getForObject("/scores/123", String.class);
		// Ergebnis: {"customerId":"123","score":69}
		log.info("Response {}", stringResponse);
		
		
		ResponseEntity<CreditScoreResponse> forEntity 
			= restTemplate.getForEntity("/scores/123", CreditScoreResponse.class);
		// Ergebnis: Objekt Response <200,CustomerService.CreditScoreResponse(customerId=123, score=52),[Content-Type:"application/json", Transfer-Encoding:"chunked", Date:"Sat, 28 Nov 2020 17:50:02 GMT", Keep-Alive:"timeout=60", Connection:"keep-alive"]>
		log.info("Response {}", forEntity);

		CreditScoreResponse forObject 
			= restTemplate.getForObject("/scores/123", CreditScoreResponse.class);
		// Ergebnis: Objekt CreditScoreResponse(customerId=123, score=12)
		log.info("Response {}", forObject);
		
		/*
		 * Aufgabe 2
		 */
		restTemplate.postForLocation("/scores/123", new CreditScoreUpload("any", 1200L), Void.class);
		
		/*
		 * Aufgabe 3
		 */
		try {
			restTemplate.getForObject("/scores/timeout", CreditScoreResponse.class);
		} catch (Exception e) {
			log.info("Exception Timeout - ",e);
		}

		/*
		 * Fehlerfälle
		 */
		try {
			restTemplate.getForObject("/scores/400", CreditScoreResponse.class);
		} catch (Exception e) {
			log.info("Exception 400 - ",e);
		}

		try {
			restTemplate.getForObject("/scores/500", CreditScoreResponse.class);
		} catch (Exception e) {
			log.info("Exception 500 - ",e);
		}

	}

	@Setter
	@Getter
	@ToString
	public static class CreditScoreResponse {
		private String customerId;
		private Long score;
	}

	@Setter
	@Getter
	@ToString
	@AllArgsConstructor
	public static class CreditScoreUpload {
		private String scoreType;
		private Long score;
	}
	
}
