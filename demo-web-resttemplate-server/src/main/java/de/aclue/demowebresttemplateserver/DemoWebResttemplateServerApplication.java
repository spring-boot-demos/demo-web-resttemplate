package de.aclue.demowebresttemplateserver;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@RestController
@Slf4j
public class DemoWebResttemplateServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebResttemplateServerApplication.class, args);
	}

	@GetMapping("/scores/{userId}")
	public CreditScoreResponse getScore(@PathVariable String userId) throws InterruptedException {

		// error cases
		if (userId.equals("400")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		} else if (userId.equals("500")) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		} else if (userId.equals("timeout")) {
			Thread.sleep(20 * 1000);
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// regular response
		CreditScoreResponse response = new CreditScoreResponse();
		response.setCustomerId(userId);
		response.setScore(randomScore());
		return response;
	}

	@PostMapping("/scores/{userId}")
	public void postScore(@PathVariable String userId, @RequestBody @Valid CreditScoreInput creditScoreInput) {
		log.info("Score erfolgreich empfangen für user={} - {}", userId, creditScoreInput);
	}

	private long randomScore() {
		long leftLimit = 0L;
		long rightLimit = 100L;
		return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
	}

	@Setter
	@Getter
	public static class CreditScoreResponse {
		private String customerId;
		private Long score;
	}

	@Setter
	@Getter
	@ToString
	public static class CreditScoreInput {
		@NotNull
		private String scoreType;

		@NotNull
		@Min(1000)
		@Max(2000)
		private Long score;
	}

}
