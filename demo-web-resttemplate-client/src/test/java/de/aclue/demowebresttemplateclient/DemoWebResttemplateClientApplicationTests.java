package de.aclue.demowebresttemplateclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(
		properties = { "foo=bar", "soll.ich.testen=unbedingt" }, 
		webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoWebResttemplateClientApplicationTests {

	@Test
	void contextLoads() {
	}

}
